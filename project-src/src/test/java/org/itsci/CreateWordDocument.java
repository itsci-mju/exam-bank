package org.itsci;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateWordDocument {
    public static void test() throws IOException, InvalidFormatException {
        int fontSize = 14;
        String term = "1";
        String year = "2566";
        String subject = "ทส103 กระบวนการพัฒนาซอฟต์แวร์";
        String date = "20/01/2566";
        String time = "09.00 น.";
        String author = "อาจารย์ ดร.วัชรินทร์  สาระไชย";
        String authorContact = "0819615100";
        String numOfPage = "10";
        String totalScore = "140";

        // Create a new Word document
        XWPFDocument document = new XWPFDocument(new FileInputStream("Midterm_2-2565.docx"));

        XWPFStyles styles = document.getStyles();
        String styleName = "Heading1";

        List<XWPFTable> tables = document.getTables();
        XWPFTable table = tables.get(0);

        XWPFTableRow row2 = table.getRow(1);
        XWPFTableCell cell1 = row2.getCell(2);
        XWPFTableCell cell2 = row2.getCell(4);
        setParagraphText(cell1, term);
        setParagraphText(cell2, year);

        XWPFTableRow row3 = table.getRow(2);
        cell1 = row3.getCell(1);
        setParagraphText(cell1, subject);

        XWPFTableRow row4 = table.getRow(3);
        cell1 = row4.getCell(1);
        cell2 = row4.getCell(3);
        setParagraphText(cell1, date);
        setParagraphText(cell2, time);

        XWPFTableRow row5 = table.getRow(4);
        cell1 = row5.getCell(1);
        cell2 = row5.getCell(3);
        setParagraphText(cell1, author);
        setParagraphText(cell2, authorContact);

        // Create a new paragraph
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        paragraph.setStyle("No1");
        run.setText("คำชี้แจงรายวิชาสอบ");

        List<String> documentList = new ArrayList<String>(
                Arrays.asList(
                        "ข้อสอบประกอบด้วย " + numOfPage + " หน้ารวมปก",
                        "คะแนนเต็ม " + totalScore + " คะแนน",
                        "อนุญาตให้นำเครื่องคำนวณ เข้าห้องสอบ"));
        CTAbstractNum cTAbstractNum = CTAbstractNum.Factory.newInstance();
        //Next we set the AbstractNumId. This requires care.
        //Since we are in a new document we can start numbering from 0.
        //But if we have an existing document, we must determine the next free number first.
        cTAbstractNum.setAbstractNumId(BigInteger.valueOf(0));
        /* Bullet list
          CTLvl cTLvl = cTAbstractNum.addNewLvl();
          cTLvl.setIlvl(BigInteger.valueOf(0)); // set indent level 0
          cTLvl.addNewNumFmt().setVal(STNumberFormat.BULLET);
          cTLvl.addNewLvlText().setVal("•");
        */
        ///* Decimal list
        CTLvl cTLvl = cTAbstractNum.addNewLvl();
        cTLvl.setIlvl(BigInteger.valueOf(0)); // set indent level 0
        cTLvl.addNewNumFmt().setVal(STNumberFormat.DECIMAL);
        cTLvl.addNewLvlText().setVal("%1.");
        cTLvl.addNewStart().setVal(BigInteger.valueOf(1));
        //*/
        XWPFAbstractNum abstractNum = new XWPFAbstractNum(cTAbstractNum);
        XWPFNumbering numbering = document.createNumbering();
        BigInteger abstractNumID = numbering.addAbstractNum(abstractNum);
        BigInteger numID = numbering.addNum(abstractNumID);
        for (String string : documentList) {
            paragraph = document.createParagraph();
            paragraph.setIndentationLeft(420);
            paragraph.setFirstLineIndent(-400);
            paragraph.setNumID(numID);
            run = paragraph.createRun();
            run.setFontFamily("TH SarabunPSK");
            run.setFontSize(fontSize);
            run.setText(string);
        }
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        run.addBreak(BreakType.PAGE);

        createSecction(document, "ตอนที่ 1 จงเลือกคำตอบที่ถูกต้องจากตัวเลือก A-D เพียงหนึ่งคำตอบแล้วกากบาทลงในกระดาษคำตอบที่กำหนดให้:");
        createSecction(document, "ตอนที่ 2 จงเลือกคำตอบที่ถูกต้องจากตัวเลือก A-D เพียงหนึ่งคำตอบแล้วกากบาทลงในกระดาษคำตอบที่กำหนดให้:");

        document.write(new FileOutputStream("updated_document.docx"));
        document.close();
    }

    private static void createSecction(XWPFDocument document, String cmdTxt) throws IOException, InvalidFormatException {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setStyle("No1");
        XWPFRun run = paragraph.createRun();
        run.setText(cmdTxt);

        for (int i = 1; i < 5; i++) {
            JSONArray ja = new JSONArray();
            ja.put(createParagraphText("question", i + "."));
            ja.put(createParagraphControl("question", "tab"));
            ja.put(createParagraphText("question", "ข้อใดคือโค้ดที่แสดงข้อความ “Hello World”"));
            JSONArray list = new JSONArray();
            list.put(createParagraphText("code", "int i=97;"));
            list.put(createParagraphControl("code", "break"));
            list.put(createParagraphText("code", "float x=3.14;"));
            list.put(createParagraphControl("code", "break"));
            list.put(createParagraphText("code", "printf(\"My favorite numbers are %d and %f.\\n\", i, x);"));
            ja.put(list);

            String jsonString = ja.toString();
            JSONArray jsonArray = new JSONArray(jsonString);

            createMultipleQuestion(document, jsonArray);
        }
    }

    private static JSONObject createParagraphText(String style, String text) {
        JSONObject jo = new JSONObject();
        jo.put("style", style);
        jo.put("text", text);
        return jo;
    }

    private static JSONObject createParagraphControl(String style, String control) {
        JSONObject jo = new JSONObject();
        jo.put("style", style);
        jo.put("control", control);
        return jo;
    }

    private static void createMultipleQuestion(XWPFDocument document, JSONArray questionArray) throws IOException, InvalidFormatException {
        generateText(document, null, "", questionArray);

        JSONArray choiceArray = new JSONArray();
        choiceArray.put(createParagraphText("choice", "A."));
        choiceArray.put(createParagraphControl("choice", "tab"));
        choiceArray.put(createParagraphText("choice", "include stdio.h"));
        choiceArray.put(createParagraphControl("choice", "break"));
        choiceArray.put(createParagraphText("choice", "int main() { printf(“Helloworld”); }"));
        generateText(document, null, "", choiceArray);

        choiceArray = new JSONArray();
        choiceArray.put(createParagraphText("choice", "B."));
        choiceArray.put(createParagraphControl("choice", "tab"));
        choiceArray.put(createParagraphText("choice", "include <stdio.h>"));
        choiceArray.put(createParagraphControl("choice", "break"));
        choiceArray.put(createParagraphText("choice", "int main() { display(“Helloworld”); }"));
        generateText(document, null, "", choiceArray);

        choiceArray = new JSONArray();
        choiceArray.put(createParagraphText("choice", "C."));
        choiceArray.put(createParagraphControl("choice", "tab"));
        choiceArray.put(createParagraphText("choice", "#include <stdio.h>"));
        choiceArray.put(createParagraphControl("choice", "break"));
        choiceArray.put(createParagraphText("choice", "int main() { printf(“Helloworld”); return 0; }"));
        generateText(document, null, "", choiceArray);

        choiceArray = new JSONArray();
        choiceArray.put(createParagraphText("choice", "D."));
        choiceArray.put(createParagraphControl("choice", "tab"));
        choiceArray.put(createParagraphText("choice", "#include <stdio.h>"));
        choiceArray.put(createParagraphControl("choice", "break"));
        choiceArray.put(createParagraphText("choice", "int main() { printf(“Helloworld”); return 0; }"));
        generateText(document, null, "", choiceArray);
    }

    private static void generateText(XWPFDocument document, XWPFParagraph paragraph, String style, Object object) {
        assert (style != null);

        if (object instanceof JSONObject jsonObject) {
            assert (paragraph != null);
            insertObject(paragraph, jsonObject);
        } else {
            assert (object instanceof JSONArray);
            JSONArray jsonArray = (JSONArray) object;
            for (int i=0; i<jsonArray.length(); i++) {
                Object subObject = jsonArray.get(i);
                if (subObject instanceof JSONObject) {
                    JSONObject jsonObject = (JSONObject) subObject;
                    String curStyle = jsonObject.getString("style");
                    if (paragraph == null || !style.equals(curStyle)) {
                        paragraph = document.createParagraph();
                        style = curStyle;
                    }
                    paragraph.setStyle(style);
                    generateText(document, paragraph, style, jsonObject);
                } else {
                    generateText(document, paragraph, style, subObject);
                }
            }
        }
    }

    private static void insertObject(XWPFParagraph paragraph, JSONObject jsonObject) {
        XWPFRun run = paragraph.createRun();
        if (jsonObject.has("text")) {
            run.setText(jsonObject.getString("text"));
        } else if (jsonObject.has("control")) {
            String ctl = jsonObject.getString("control");
            if ("tab".equals(ctl)) {
                CTR ctr = run.getCTR();
                ctr.addNewTab();
            } else if ("break".equals(ctl)) {
                run.addBreak();
            }
        }
    }

    private static void createMultipleChoice(XWPFDocument document, String choice, String[] choices) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setStyle("choice");
        XWPFRun run = paragraph.createRun();

        run.setText(choice);
        run.addTab();

        for (String text : choices) {
            if ("break".equals(text)) {
                run.addBreak();
            } else {
                run.setText(text);
            }
        }
    }

    private static void setParagraphText(XWPFTableCell cell, String text) {
        XWPFParagraph p = cell.getParagraphs().get(0);
        XWPFRun run = p.getRuns().get(0);
//        String text = run.getText(0);
        run.setText(text, 0);
    }

    private static void addCustomHeadingStyle(XWPFStyles styles, String strStyleId, int headingLevel, int pointSize, String hexColor) {

        CTStyle ctStyle = CTStyle.Factory.newInstance();
        ctStyle.setStyleId(strStyleId);


        CTString styleName = CTString.Factory.newInstance();
        styleName.setVal(strStyleId);
        ctStyle.setName(styleName);

        CTDecimalNumber indentNumber = CTDecimalNumber.Factory.newInstance();
        indentNumber.setVal(BigInteger.valueOf(headingLevel));

        // lower number > style is more prominent in the formats bar
        ctStyle.setUiPriority(indentNumber);

        CTOnOff onoffnull = CTOnOff.Factory.newInstance();
        ctStyle.setUnhideWhenUsed(onoffnull);

        // style shows up in the formats bar
        ctStyle.setQFormat(onoffnull);

        // style defines a heading of the given level
//        CTPPr ppr = CTPPr.Factory.newInstance();
//        ppr.setOutlineLvl(indentNumber);
//        ctStyle.setPPr(ppr);

        XWPFStyle style = new XWPFStyle(ctStyle);

        CTHpsMeasure size = CTHpsMeasure.Factory.newInstance();
        size.setVal(new BigInteger(String.valueOf(pointSize)));
        CTHpsMeasure size2 = CTHpsMeasure.Factory.newInstance();
        size2.setVal(new BigInteger("24"));

        CTFonts fonts = CTFonts.Factory.newInstance();
        fonts.setAscii("Loma");

        CTRPr rpr = CTRPr.Factory.newInstance();
        rpr.setRFontsArray(new CTFonts[]{fonts});
        rpr.setSzArray(new CTHpsMeasure[]{size});
        rpr.setSzCsArray(new CTHpsMeasure[]{size2});

        CTColor color = CTColor.Factory.newInstance();
        color.setVal(hexToBytes(hexColor));
        rpr.setColorArray(new CTColor[]{color});
        style.getCTStyle().setRPr(rpr);
        // is a null op if already defined

        style.setType(STStyleType.PARAGRAPH);
        styles.addStyle(style);

    }

    public static byte[] hexToBytes(String hexString) {
        HexBinaryAdapter adapter = new HexBinaryAdapter();
        byte[] bytes = adapter.unmarshal(hexString);
        return bytes;
    }

//    XWPFStyles styles = document.createStyles();
//    String heading1 = "My Heading 1";
//    String heading2 = "My Heading 2";
//    String heading3 = "My Heading 3";
//    String heading4 = "My Heading 4";
//    addCustomHeadingStyle(styles, heading1, 1, 36, "4288BC");
//    addCustomHeadingStyle(styles, heading2, 2, 28, "4288BC");
//    addCustomHeadingStyle(styles, heading3, 3, 24, "4288BC");
//    addCustomHeadingStyle(styles, heading4, 4, 20, "000000");
}
