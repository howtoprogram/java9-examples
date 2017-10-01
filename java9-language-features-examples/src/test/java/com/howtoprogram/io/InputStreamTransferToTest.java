package com.howtoprogram.io;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputStreamTransferToTest {

    @Test
    public void testTransferTo() throws IOException {

        ByteArrayInputStream bis = new ByteArrayInputStream("Hello".getBytes());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {


            long count = bis.transferTo(bos);

            byte[] bytes = bos.toByteArray();

            assertEquals(count, bytes.length);


        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }

    @Test
    public void testTransferToJava9Syntax() throws IOException {

        ByteArrayInputStream bis = new ByteArrayInputStream("Hello".getBytes());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try (bis; bos) {
            
            long count = bis.transferTo(bos);

            byte[] bytes = bos.toByteArray();
            assertEquals(count, bytes.length);
        }
    }
}
