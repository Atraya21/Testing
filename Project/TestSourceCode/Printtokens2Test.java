package com.example.java;
import junit.framework.Assert;
import org.junit.Rule;
import org.junit.Test;
import sun.applet.Main;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import static org.junit.Assert.*;

public class Printtokens2Test {

    Printtokens2 pTest = new Printtokens2();
    BufferedReader br = pTest.open_character_stream("file.txt");

    // 1.
    @Test
    public void open_character_stream() throws Exception {
        // given a file name
        br = pTest.open_character_stream("file.txt");
        assertSame(br.getClass(),BufferedReader.class);

        // input is null
        br = pTest.open_character_stream(null);
        assertSame(br.getClass(),BufferedReader.class);
        assertEquals("Enter file name: ", "Enter file name: ");

        // input string is empty
        br = pTest.open_character_stream("");
        assertSame(br.getClass(),BufferedReader.class);
        assertEquals("Enter file name: ", "Enter file name: ");

        // file does not exist
        try {
            br = pTest.open_character_stream("file");
        }
        catch (Exception e){
            Assert.fail();
        }
    }

    // 2.
    @Test
    public void get_char() throws Exception {
        char ct = pTest.get_char(br);
        if (Character.isDefined(ct)) {
            assert true;
        }
        else {
            fail();
        }
    }

    // 3.
    @Test
    public void testUnget_char() throws Exception {
        br.mark(4);
        char ch = 'a';
        char ct = pTest.unget_char(ch,br);
        if (Character.isDefined(ct)) {
            assert true;
        }
        else {
            fail();
        }
        ch = '1';
        ct = pTest.unget_char(ch, br);
        if (Character.isDefined(ct)) {
            assert true;
        }
        else {
            fail();
        }
        ch = '!';
        ct = pTest.unget_char(ch, br);
        if (Character.isDefined(ct)) {
            assert true;
        }
        else {
            fail();
        }
    }

    // 4.
    @Test
    public void testOpen_token_stream() throws Exception {

        br = pTest.open_token_stream("file.txt");
        assertSame(br.getClass(),BufferedReader.class);

        br = pTest.open_token_stream(null);
        assertSame(br.getClass(), BufferedReader.class);

        try {
            br = pTest.open_token_stream("file");        }
        catch (Exception e){
            Assert.fail();
        }
    }

    
    //5.
    @Test
    public void testGet_token() throws Exception {
        br = pTest.open_token_stream("file.txt");
        int res = pTest.get_char(br);
        if (res == -1) {
            assertEquals(pTest.get_token(br), null);
        }
        assertEquals(pTest.get_token(br).getClass(), String.class);
    }


    //.6
    @Test
    public void testIs_token_end() throws Exception {

    }

    // 7.
    @Test
    public void testToken_type() throws Exception {
        assertEquals(pTest.token_type("and"),1);
        assertEquals(pTest.token_type("or"),1);
        assertEquals(pTest.token_type("if"),1);
        assertEquals(pTest.token_type("xor"),1);
        assertEquals(pTest.token_type("lambda"),1);
        assertEquals(pTest.token_type("=>"),1);

        assertEquals(pTest.token_type("("),2);
        assertEquals(pTest.token_type(")"),2);
        assertEquals(pTest.token_type("["),2);
        assertEquals(pTest.token_type("]"),2);
        assertEquals(pTest.token_type("'"),2);
        assertEquals(pTest.token_type("`"),2);

        assertEquals(pTest.token_type("is1d"),3);
        assertEquals(pTest.token_type("token"),3);

        assertEquals(pTest.token_type("1"),41);
        assertEquals(pTest.token_type("1111"),41);

        assertEquals(pTest.token_type("\"\""),42);
        assertEquals(pTest.token_type("\"abc\""),42);

        assertEquals(pTest.token_type("#a"),43);

        //assertEquals(pTest.token_type("//"),5);
    }

    // 8.
    @Test
    public void testPrint_token() throws Exception {
        pTest.print_token("1error");
        pTest.print_token("and");
        pTest.print_token("("); //special sym
        pTest.print_token("1");
        pTest.print_token("identifer");
        pTest.print_token("\"String\"");
        pTest.print_token("//comment");
        pTest.print_token("#c");
    }

    // 9.
    @Test
    public void testIs_comment() throws Exception {
        assertTrue(pTest.is_comment("//"));
    }

    // 10.
    @Test
    public void is_keyword() throws Exception {
        assertTrue(pTest.is_keyword("and"));
        assertTrue(pTest.is_keyword("or"));
        assertTrue(pTest.is_keyword("if"));
        assertTrue(pTest.is_keyword("xor"));
        assertTrue(pTest.is_keyword("lambda"));
        assertTrue(pTest.is_keyword("=>"));
        assertFalse(pTest.is_keyword("testing"));
    }

    // 11.
    @Test
    public void testIs_char_constant() throws Exception {
        assertFalse(pTest.is_char_constant("a"));
        assertTrue(pTest.is_char_constant("#a"));

    }

    // 12.
    @Test
    public void testIs_num_constant() throws Exception {
        assertFalse(pTest.is_num_constant("c"));
        assertFalse(pTest.is_num_constant("1c"));

        assertTrue(pTest.is_num_constant("1"));
        assertTrue(pTest.is_num_constant("111111"));

    }

    // 13.
    @Test
    public void testIs_str_constant() throws Exception {
        assertFalse(pTest.is_str_constant("\""));
        assertFalse(pTest.is_str_constant("\"abc"));

        assertTrue(pTest.is_str_constant("\"\""));
        assertTrue(pTest.is_str_constant("\"abc\""));
    }

    // 14.
    @Test
    public void testIs_identifier() throws Exception {
        assertFalse(pTest.is_identifier("1"));
        assertFalse(pTest.is_identifier("is!d"));

        assertTrue(pTest.is_identifier("token"));


    }

    //15.
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Test
    public void testUnget_error() throws Exception {
        pTest.unget_error(br);
        assertEquals("It can not get charcter\n", "It can not get charcter\n");

    }

  
    // 17.
    @Test
    public void testPrint_spec_symbol() throws Exception {
        pTest.print_spec_symbol("(");
        pTest.print_spec_symbol(")");
        pTest.print_spec_symbol("[");
        pTest.print_spec_symbol("]");
        pTest.print_spec_symbol("\'");
        pTest.print_spec_symbol("`");
        pTest.print_spec_symbol(",");

        //System.setOut(new PrintStream(pTest.print_spec_symbol(")")));
        //assertEquals(System.out., );
    }

    // 18
    @Test
    public void testIs_spec_symbol() throws Exception {
        assertTrue(pTest.is_spec_symbol('('));
        assertTrue(pTest.is_spec_symbol(')'));
        assertTrue(pTest.is_spec_symbol('['));
        assertTrue(pTest.is_spec_symbol('\''));
        assertTrue(pTest.is_spec_symbol('`'));
        assertTrue(pTest.is_spec_symbol(','));

        assertFalse(pTest.is_spec_symbol('d'));
        assertFalse(pTest.is_spec_symbol('1'));
    }

    @Test
    public void testMain() throws Exception {
        String[] args = {"file.txt"};
        pTest.main(args);
    }
}
