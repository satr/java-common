package com.github.satr.java.common;

import com.github.satr.common.OperationResultImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OperationResultImplTest {

    private OperationResultImpl result;

    @Before
    public void setUp() throws Exception {
        result = new OperationResultImpl();
    }

    @Test
    public void testDefaultIsSuccess() {
        assertTrue(result.isSuccess());
        assertFalse(result.isFailed());
    }

    @Test
    public void addError() {
        String message = "test";
        result.addError(message);

        assertTrue(result.getErrors().contains(message));
        assertTrue(result.getErrorsAsString().contains(message));
        assertTrue(result.getHistory().get(0).endsWith(message));
    }

    @Test
    public void addErrorToHistory() {
        String message = "test";
        result.addError(message);

        assertTrue(result.getErrors().contains(message));
        assertTrue(result.getHistory().get(0).endsWith(message));
        assertTrue(result.getHistoryAsString().contains(message));
    }

    @Test
    public void addHistoryForAllMessageSeveritiesInOrderOfAdding() {
        String messageError1 = "test-error";
        result.addError(messageError1);
        String messageWarn1 = "test-warn";
        result.addWarning(messageWarn1);
        String messageInfo1 = "test-info";
        result.addInfo(messageInfo1);
        String messageVerb1 = "test-verb";
        result.addVerbose(messageVerb1);
        String messageError2 = "test-error2";
        result.addError(messageError2);
        String messageWarn2 = "test-warn2";
        result.addWarning(messageWarn2);
        String messageInfo2 = "test-info2";
        result.addInfo(messageInfo2);
        String messageVerb2 = "test-verb2";
        result.addVerbose(messageVerb2);

        assertTrue(result.getErrors().contains(messageError1));
        assertTrue(result.getErrors().contains(messageError2));
        assertTrue(result.getWarning().contains(messageWarn1));
        assertTrue(result.getWarning().contains(messageWarn2));
        assertTrue(result.getInfos().contains(messageInfo1));
        assertTrue(result.getInfos().contains(messageInfo2));
        assertTrue(result.getVerbose().contains(messageVerb1));
        assertTrue(result.getVerbose().contains(messageVerb2));
        assertTrue(result.getHistory().get(0).endsWith(messageError1));
        assertTrue(result.getHistory().get(1).endsWith(messageWarn1));
        assertTrue(result.getHistory().get(2).endsWith(messageInfo1));
        assertTrue(result.getHistory().get(3).endsWith(messageVerb1));
        assertTrue(result.getHistory().get(4).endsWith(messageError2));
        assertTrue(result.getHistory().get(5).endsWith(messageWarn2));
        assertTrue(result.getHistory().get(6).endsWith(messageInfo2));
        assertTrue(result.getHistory().get(7).endsWith(messageVerb2));
    }

    @Test
    public void testNotSuccessOnAddError() {
        String message = "test";

        result.addError(message);

        assertFalse(result.isSuccess());
        assertTrue(result.isFailed());
    }

    @Test
    public void testResetSuccessStatus() {
        String message = "test";
        result.addError(message);

        result.resetSuccessState();

        assertTrue(result.isSuccess());
        assertTrue(result.getErrors().contains(message));
        assertTrue(result.getHistory().get(0).endsWith(message));
    }
}