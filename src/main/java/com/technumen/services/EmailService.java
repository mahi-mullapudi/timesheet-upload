package com.technumen.services;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * Common Mail Service to send mails.
 * <b><u>NOTE:</u></b>
 * 1. Bcc is NOT supported, for business transparency.
 * 2. No Validations are performed for validity of email addresses.
 * 3. No Validations for size of attachments.
 * 4. No Validation for the type of attachments.
 * 5. <b>Exception Handling</b> - some methods throws exception which
 * needs to be handled according to business need i.e. to stop the
 * execution flow in case of execution (or) silently log it and move on.
 */
public interface EmailService {

    /**
     * Service method to send simple plain text mail without attachments.
     *
     * @param fromAddress String
     * @param toAddress   String
     * @param ccAddress   String
     * @param subject     String
     * @param mailContent String
     */
    public void sendPlainTextMailWithoutAttachment(String fromAddress, String toAddress, String ccAddress, String subject, String mailContent);

    /**
     * Service method to send plain text mail <b>WITH</b> multiple attachments.
     *
     * @param fromAddress String
     * @param toAddress   String
     * @param ccAddress   String
     * @param subject     String
     * @param mailContent String of mail content.
     * @param attachFiles List of files attached.
     *                    For single files add it to list.
     *                    Ex: Arrays.asList(uploadedFile)
     */
    public void sendPlainTextMailWithAttachment(String fromAddress, String toAddress, String ccAddress, String subject, String mailContent, List<MultipartFile> attachFiles);

    /**
     * Service method to send simple plain text mail to multiple recipients
     * <b>without</b> attachment(s).
     *
     * @param fromAddress String
     * @param toAddress   List<String>
     * @param ccAddress   List<String>
     * @param subject     String
     * @param mailContent String of mail content.
     */
    public void sendBulkPlainTextMailWithoutAttachment(String fromAddress, List<String> toAddress, List<String> ccAddress, String subject, String mailContent);

    /**
     * Service method to send simple plain text mail to multiple recipients
     * <b>WITH</b> attachment(s).
     *
     * @param fromAddress String
     * @param toAddress   List<String>
     * @param ccAddress   List<String>
     * @param subject     String
     * @param mailContent String of mail content.
     * @param attachFiles List of files attached.
     *                    For single files add it to list.
     *                    Ex: Arrays.asList(uploadedFile)
     */
    public void sendBulkPlainTextMailWithAttachment(String fromAddress, List<String> toAddress, List<String> ccAddress, String subject, String mailContent, List<MultipartFile> attachFiles);

    /**
     * Send Mail which has template defined in database (FNS_APP_EMAIL_TEMPLATE)
     * <b>without</b> attachment.
     *
     * @param fromAddress         String
     * @param toAddress           String
     * @param ccAddress           String
     * @param subject             String
     * @param modelForMailContent Map of ALL key value parameters used in the
     *                            mail template. If keys that are used in mail
     *                            template missed to be passed in,the outgoing
     *                            mail would contain untranslated text.
     * @param templateName        Template name defined in DSC_EMAIL_TEMPLATE_KEY
     *                            column of FNS_APP_EMAIL_TEMPLATE table.
     * @param isTemplateHtml      if mail template is HTML send true.
     *                            Pass in false for text mail template.
     * @throws java.lang.Exception
     */
    public void sendTemplateMailWithoutAttachment(String fromAddress, String toAddress, String ccAddress, String subject, Map<String, Object> modelForMailContent, String templateName, boolean isTemplateHtml) throws Exception;

    /**
     * Send Mail which has template defined in database (FNS_APP_EMAIL_TEMPLATE)
     * <b>WITH</b> attachment(s).
     *
     * @param fromAddress         String
     * @param toAddress           String
     * @param ccAddress           String
     * @param subject             String
     * @param modelForMailContent Map of ALL key value parameters used in the
     *                            mail template. If keys that are used in mail
     *                            template missed to be passed in,the outgoing
     *                            mail would contain untranslated text.
     * @param templateName        Map of ALL key value parameters used in the
     *                            mail template. If keys that are used in mail
     *                            template missed to be passed in,the outgoing
     *                            mail would contain untranslated text.
     * @param isTemplateHtml      if mail template is HTML send true.
     *                            Pass in false for text mail template.
     * @param attachFiles         List of files attached.
     *                            For single files add it to list.
     *                            Ex: Arrays.asList(uploadedFile)
     * @throws java.lang.Exception Exception handling left to callers
     *                             discretion - should error be shown to users
     *                             or to log the error and continue execution.
     */
    public void sendTemplateMailWithAttachment(String fromAddress, String toAddress, String ccAddress, String subject, Map<String, Object> modelForMailContent, String templateName, boolean isTemplateHtml, List<MultipartFile> attachFiles) throws Exception;

    /**
     * Send Mail to multiple recipients, which has template defined
     * in database (FNS_APP_EMAIL_TEMPLATE) <b>without</b> attachment.
     *
     * @param fromAddress         String
     * @param toAddress           List<String>
     * @param ccAddress           List<String>
     * @param subject             String
     * @param modelForMailContent Map of ALL key value parameters used in the
     *                            mail template. If keys that are used in mail
     *                            template missed to be passed in,the outgoing
     *                            mail would contain untranslated text.
     * @param templateName        Map of ALL key value parameters used in the
     *                            mail template. If keys that are used in mail
     *                            template missed to be passed in,the outgoing
     *                            mail would contain untranslated text.
     * @param isTemplateHtml      if mail template is HTML send true.
     *                            Pass in false for text mail template.
     * @throws java.lang.Exception Exception handling left to callers
     *                             discretion - should error be shown to users
     *                             or to log the error and continue execution.
     */
    public void sendBulkTemplateMailWithoutAttachment(String fromAddress, List<String> toAddress, List<String> ccAddress, String subject, Map<String, Object> modelForMailContent, String templateName, boolean isTemplateHtml) throws Exception;

    /**
     * Send Mail to multiple recipients, which has template defined
     * in database (FNS_APP_EMAIL_TEMPLATE) <b>WITH</b> attachment(s).
     *
     * @param fromAddress         String
     * @param toAddress           List<String>
     * @param ccAddress           List<String>
     * @param subject             String
     * @param modelForMailContent Map of ALL key value parameters used in the
     *                            mail template. If keys that are used in mail
     *                            template missed to be passed in,the outgoing
     *                            mail would contain untranslated text.
     * @param templateName        Map of ALL key value parameters used in the
     *                            mail template. If keys that are used in mail
     *                            template missed to be passed in,the outgoing
     *                            mail would contain untranslated text.
     * @param isTemplateHtml      if mail template is HTML send true.
     *                            Pass in false for text mail template.
     * @param attachFiles         List of files attached.
     *                            For single files add it to list.
     *                            Ex: Arrays.asList(uploadedFile)
     * @throws java.lang.Exception Exception handling left to callers
     *                             discretion - should error be shown to users
     *                             or to log the error and continue execution.
     */
    public void sendBulkTemplateMailWithAttachment(String fromAddress, List<String> toAddress, List<String> ccAddress, String subject, Map<String, Object> modelForMailContent, String templateName, boolean isTemplateHtml, List<MultipartFile> attachFiles) throws Exception;

    /**
     * @param fromAddress
     * @param toAddress
     * @param ccAddress
     * @param subject
     * @param mailContent
     * @throws Exception
     */
    public void sendMimeMailWithoutAttachment(String fromAddress, List<String> toAddress, List<String> ccAddress, String subject, String mailContent, boolean isMailHtml) throws Exception;
}

