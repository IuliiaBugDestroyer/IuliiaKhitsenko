package com.epam.tc.hw4;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import java.io.InputStream;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AttachmentUtils {
    @Attachment
    public String makeStringAttachment(final List<String> attachmentStrings) {
        return attachmentStrings.toString();
    }

    public void attachImageFromInputStream(InputStream inputStream) {
        Allure.addAttachment("image from IS", inputStream);
    }

    @Attachment(type = "image/png", value = "try to use param {name}")
    public byte[] makeImageAttachment(final String name, final byte[] source) {
        return source;
    }
}
