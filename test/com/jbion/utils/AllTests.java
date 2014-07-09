package com.jbion.utils;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.jbion.utils.io.binary.TestBitStream;

@RunWith(Suite.class)
@SuiteClasses({ TestBitStream.class })
public class AllTests {

}
