/*
 * Copyright (c) 2005 Opensymphony. All Rights Reserved.
 */
package com.opensymphony.xwork.util;

import com.opensymphony.xwork.XWorkTestCase;

import java.util.List;
import java.util.ArrayList;

/**
 * XWorkListPropertyAccessorTest
 * <p/>
 * Created : Nov 7, 2005 3:54:44 PM
 *
 * @author Jason Carreira <jcarreira@eplus.com>
 */
public class XWorkListPropertyAccessorTest extends XWorkTestCase {

    public void testContains() {
        OgnlValueStack vs = new OgnlValueStack();
        ListHolder listHolder = new ListHolder();
        vs.push(listHolder);

        vs.setValue("longs", new String[] {"1", "2", "3"});

        assertNotNull(listHolder.getLongs());
        assertEquals(3, listHolder.getLongs().size());
        assertEquals(new Long(1), (Long) listHolder.getLongs().get(0));
        assertEquals(new Long(2), (Long) listHolder.getLongs().get(1));
        assertEquals(new Long(3), (Long) listHolder.getLongs().get(2));

        assertTrue(((Boolean) vs.findValue("longs.contains(1)")).booleanValue());
    }

    public void testCanAccessListSizeProperty() {
        OgnlValueStack vs = new OgnlValueStack();
        List myList = new ArrayList();
        myList.add("a");
        myList.add("b");

        ListHolder listHolder = new ListHolder();
        listHolder.setStrings(myList);

        vs.push(listHolder);

        assertEquals(new Integer(myList.size()), vs.findValue("strings.size()"));
        assertEquals(new Integer(myList.size()), vs.findValue("strings.size"));
    }
}
