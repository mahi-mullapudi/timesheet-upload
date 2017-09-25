package com.technumen.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class FileNameUtilsTest {

    FileNameUtils ecasFileUtils = new FileNameUtils();

    @Test
    public void testValidFileName() {
        assertEquals("Wrong File Name", true, ecasFileUtils.validFileName("test1.pdf"));
        assertEquals("Wrong File Name", true, ecasFileUtils.validFileName("test1.pdf"));
        assertEquals("Wrong File Name ", true, ecasFileUtils.validFileName("teadf.sadf.jpg"));
        assertEquals("Wrong File Name ", true, ecasFileUtils.validFileName("test.jpeg"));
        assertEquals("Wrong File Name ", true, ecasFileUtils.validFileName("test file. ghit .png"));
        assertEquals("Wrong File Name ", true, ecasFileUtils.validFileName("file msg it.fns.txt"));
        //Non-allowed File Names
        assertEquals("Wrong File Name ", false, ecasFileUtils.validFileName("test@$sadfsad"));
        assertEquals("Wrong File Name ", false, ecasFileUtils.validFileName("testsd/sdf%sfsdf"));
        assertEquals("Wrong File Name ", false, ecasFileUtils.validFileName("tes!fsdf.com"));
        assertEquals("Wrong File Name ", false, ecasFileUtils.validFileName("tes@fsdf.com"));
        assertEquals("Wrong File Name ", false, ecasFileUtils.validFileName("tes#fsdf.com"));
        assertEquals("Wrong File Name ", false, ecasFileUtils.validFileName("tes$fsdf.com"));
        assertEquals("Wrong File Name ", false, ecasFileUtils.validFileName("tes%^fsdf.com"));
        assertEquals("Wrong File Name ", false, ecasFileUtils.validFileName("tes&&fsdf.com"));
        assertEquals("Wrong File Name ", false, ecasFileUtils.validFileName("tes()fsdf.com"));
        assertEquals("Wrong File Name ", false, ecasFileUtils.validFileName("tes(fsdf.com"));
        assertEquals("Wrong File Name ", false, ecasFileUtils.validFileName("tes)&^fsdf.com"));
        assertEquals("Wrong File Name ", false, ecasFileUtils.validFileName("tes@*fsdf.com"));
    }

    @Test
    public void testValidFileType() {
        assertEquals("Wrong File Type", true, ecasFileUtils.validFileType("test1.png"));
        assertEquals("Wrong File Type", true, ecasFileUtils.validFileType("test1space.jpg"));
        assertEquals("Wrong File Type", true, ecasFileUtils.validFileType("test1.jpeg"));
        assertEquals("Wrong File Type", true, ecasFileUtils.validFileType("test1.pdf.pdf"));
        assertEquals("Wrong File Type ", true, ecasFileUtils.validFileType("teadf.sadf.jpg"));
        //Test for the upper case file extensions matching
        assertEquals("Wrong File Type", true, ecasFileUtils.validFileType("TEST1.PNG"));
        assertEquals("Wrong File Type", true, ecasFileUtils.validFileType("TEST1.JPG"));
        assertEquals("Wrong File Type", true, ecasFileUtils.validFileType("TEST1.JPEG"));
        assertEquals("Wrong File Type", true, ecasFileUtils.validFileType("TEST1.PDF.PDF"));
        assertEquals("Wrong File Type ", true, ecasFileUtils.validFileType("teadf.sadf.JPG"));
        //Non-allowed file extensions
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.jpeg.exe"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.jpg.exe"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.docx.exe"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.doc.exe"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.xls.jar"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.xlsx.jar"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.xls.WAR"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.xlsx.war"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.DOCX.exe"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.XLSX.exe"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.exe"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.ppt"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.html"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.css"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.pif"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.application"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.gadget"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.JAR"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.war"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.WAR"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.BAT"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.CMD"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.JS"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.js"));
        assertEquals("Wrong File Type ", false, ecasFileUtils.validFileType("test.msp"));
    }

}