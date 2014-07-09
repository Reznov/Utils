package com.jbion.utils;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.jbion.utils.io.binary.TestBitInputStream;
import com.jbion.utils.io.binary.TestBitOutputStream;

@RunWith(Suite.class)
@SuiteClasses({ TestBitInputStream.class, TestBitOutputStream.class })
public class AllTests {

}
