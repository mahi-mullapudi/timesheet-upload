package com.technumen.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Mail Service to Send text mails and mails with template (HTML / text).
 * Service support bulk emails and multiple attachments.
 */
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendPlainTextMailWithoutAttachment(String fromAddress, String toAddress, String ccAddress, String subject, String mailContent) {
        if (StringUtils.isNotBlank(ccAddress)) {
            sendSimpleMailWithoutAttachment(fromAddress, Arrays.asList(toAddress), Arrays.asList(ccAddress), subject, mailContent);
        } else {
            sendSimpleMailWithoutAttachment(fromAddress, Arrays.asList(toAddress), new ArrayList<String>(), subject, mailContent);
        }
    }

    @Override
    public void sendPlainTextMailWithAttachment(final String fromAddress, final String toAddress, final String ccAddress, final String subject, final String mailContent, final List<MultipartFile> attachFiles) {
        if (StringUtils.isNotBlank(ccAddress)) {
            sendSimpleMailWithAttachment(fromAddress, Arrays.asList(toAddress), Arrays.asList(ccAddress), subject, mailContent, attachFiles);
        } else {
            sendSimpleMailWithAttachment(fromAddress, Arrays.asList(toAddress), new ArrayList<String>(), subject, mailContent, attachFiles);
        }
    }

    @Override
    public void sendBulkPlainTextMailWithoutAttachment(String fromAddress, List<String> toAddress, List<String> ccAddress, String subject, final String mailContent) {
        sendSimpleMailWithoutAttachment(fromAddress, toAddress, ccAddress, subject, mailContent);
    }

    @Override
    public void sendBulkPlainTextMailWithAttachment(final String fromAddress, final List<String> toAddress, final List<String> ccAddress, final String subject, final String mailContent, final List<MultipartFile> attachFiles) {
        sendSimpleMailWithAttachment(fromAddress, toAddress, ccAddress, subject, mailContent, attachFiles);
    }

    @Override
    public void sendTemplateMailWithoutAttachment(final String fromAddress, final String toAddress, final String ccAddress, final String subject,
                                                  final Map<String, Object> modelForMailContent, final String templateName, final boolean isTemplateHtml) throws Exception {

        if (StringUtils.isNotBlank(ccAddress)) {
            sendVelocityTemplateMailWithoutAttachment(fromAddress, Arrays.asList(toAddress), Arrays.asList(ccAddress), subject, modelForMailContent, templateName, isTemplateHtml);
        } else {
            sendVelocityTemplateMailWithoutAttachment(fromAddress, Arrays.asList(toAddress), new ArrayList<String>(), subject, modelForMailContent, templateName, isTemplateHtml);
        }
    }

    @Override
    public void sendBulkTemplateMailWithoutAttachment(final String fromAddress, final List<String> toAddress, final List<String> ccAddress,
                                                      final String subject, final Map<String, Object> modelForMailContent, final String templateName, final boolean isTemplateHtml) throws Exception {
        sendVelocityTemplateMailWithoutAttachment(fromAddress, toAddress, ccAddress, subject, modelForMailContent, templateName, isTemplateHtml);
    }

    @Override
    public void sendTemplateMailWithAttachment(final String fromAddress, final String toAddress, final String ccAddress, final String subject,
                                               final Map<String, Object> modelForMailContent, final String templateName, final boolean isTemplateHtml, final List<MultipartFile> attachFiles) throws Exception {

        if (StringUtils.isNotBlank(ccAddress)) {
            sendVelocityTemplateMailWithAttachment(fromAddress, Arrays.asList(toAddress), Arrays.asList(ccAddress), subject, modelForMailContent, templateName, isTemplateHtml, attachFiles);
        } else {
            sendVelocityTemplateMailWithAttachment(fromAddress, Arrays.asList(toAddress), new ArrayList<String>(), subject, modelForMailContent, templateName, isTemplateHtml, attachFiles);
        }

    }

    @Override
    public void sendBulkTemplateMailWithAttachment(String fromAddress, List<String> toAddress, List<String> ccAddress, String subject, Map<String, Object> modelForMailContent,
                                                   String templateName, boolean isTemplateHtml, List<MultipartFile> attachFiles) throws Exception {
        sendVelocityTemplateMailWithAttachment(fromAddress, toAddress, ccAddress, subject, modelForMailContent, templateName, isTemplateHtml, attachFiles);
    }

    private void sendSimpleMailWithoutAttachment(String fromAddress, List<String> toAddress, List<String> ccAddress, String subject, final String mailContent) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(fromAddress);
        email.setTo(toAddress.toArray(new String[toAddress.size()]));
        if (ccAddress != null && ccAddress.size() > 0) {
            email.setCc(ccAddress.toArray(new String[ccAddress.size()]));
        }
        email.setSubject(subject);
        email.setText(mailContent);
        mailSender.send(email);
        log.debug("Text Mail successfully sent !! \nFrom Addres: " + fromAddress + " To Address: " + toAddress + " CC Address: " + ccAddress + "\n Subject: " + subject);
    }

    private void sendSimpleMailWithAttachment(final String fromAddress, final List<String> toAddress, final List<String> ccAddress, final String subject, final String mailContent, final List<MultipartFile> attachFiles) {
        mailSender.send(new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                messageHelper.setFrom(fromAddress);
                messageHelper.setTo(toAddress.toArray(new String[toAddress.size()]));
                if (ccAddress != null && ccAddress.size() > 0) {
                    messageHelper.setCc(ccAddress.toArray(new String[ccAddress.size()]));
                }
                messageHelper.setSubject(subject);
                messageHelper.setText(mailContent);

                for (final MultipartFile attachFile : attachFiles) {
                    // determines if there is an upload file, attach it to the e-mail
                    if (attachFile != null) {
                        String attachName = attachFile.getOriginalFilename();
                        messageHelper.addAttachment(attachName, new InputStreamSource() {
                            @Override
                            public InputStream getInputStream() throws IOException {
                                return attachFile.getInputStream();
                            }
                        });
                    } else {
                        log.info("Attached file is Empty. Skipping the file " + attachFile + " in mail.");
                    }
                }
            }
        });
    }

    private void sendVelocityTemplateMailWithoutAttachment(final String fromAddress, final List<String> toAddress, final List<String> ccAddress, final String subject,
                                                           final Map<String, Object> modelForMailContent, final String templateName, final boolean isTemplateHtml) throws Exception {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setSubject(subject);
                helper.setFrom(fromAddress);
                helper.setTo(toAddress.toArray(new String[toAddress.size()]));

                if (ccAddress != null && ccAddress.size() > 0) {
                    helper.setCc(ccAddress.toArray(new String[ccAddress.size()]));
                }

                String text = geVelocityTemplateContent(modelForMailContent, templateName);
                log.debug("Template Name :" + templateName + "Template content : " + text);

                // use the true flag to indicate you need a multipart message
                helper.setText(text, isTemplateHtml);
            }
        };
        try {
            mailSender.send(preparator);
            log.debug("Template Mail without attachment successfully sent !! \nFrom Addres: " + fromAddress + " To Address: " + toAddress + " CC Address: " + ccAddress + "\n Subject: " + subject);
            log.debug(subject);
        } catch (MailException ex) {
            log.error("Error Sending Velocity Template mail: " + ex.getMessage());
            log.error("Sending mail failed for \nFrom Addres: " + fromAddress + " To Address: " + toAddress + " CC Address: " + ccAddress + "\n Subject: " + subject + " Template Name: " + templateName + " isTemplateHtml: " + isTemplateHtml);
            throw new Exception("Error Sending Velocity Template mail. " + ex.getMessage(), ex);
        }
    }

    private void sendVelocityTemplateMailWithAttachment(final String fromAddress, final List<String> toAddress, final List<String> ccAddress, final String subject,
                                                        final Map<String, Object> modelForMailContent, final String templateName, final boolean isTemplateHtml, final List<MultipartFile> attachFiles) throws Exception {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setSubject(subject);
                helper.setFrom(fromAddress);
                helper.setTo(toAddress.toArray(new String[toAddress.size()]));
                if (ccAddress != null && ccAddress.size() > 0) {
                    helper.setCc(ccAddress.toArray(new String[ccAddress.size()]));
                }

                String text = geVelocityTemplateContent(modelForMailContent, templateName);
                log.debug("Template Name :" + templateName + "Template content : " + text);
                // use the true flag to indicate you need a multipart message
                helper.setText(text, isTemplateHtml);
                for (final MultipartFile attachFile : attachFiles) {
                    // determines if there is an upload file, attach it to the e-mail
                    if (attachFile != null) {
                        log.info("inside attachFile not null block");
                        String attachName = attachFile.getOriginalFilename();
                        helper.addAttachment(attachName, new InputStreamSource() {
                            @Override
                            public InputStream getInputStream() throws IOException {
                                return attachFile.getInputStream();
                            }
                        });
                    } else {
                        log.info("Attached file is Empty. Skipping the file " + attachFile + " in mail.");
                    }
                }
            }
        };
        try {
            mailSender.send(preparator);
            log.debug("Template Mail with attachment  successfully sent !! \nFrom Addres: " + fromAddress + " To Address: " + toAddress + " CC Address: " + ccAddress + "\n Subject: " + subject);
        } catch (MailException ex) {
            log.error("Error Sending template Email: " + ex);
            throw new Exception("Error Sending Velocity Template mail with attachment. " + ex.getMessage(), ex);
        }
    }

    private String geVelocityTemplateContent(Map<String, Object> model, String templateName) throws VelocityException {
        StringBuilder content = new StringBuilder();
        try {
            content.append(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateName, "UTF-8", model));
            return content.toString();
        } catch (VelocityException e) {
            log.debug("Exception occured while processing velocity template: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void sendMimeMailWithoutAttachment(final String fromAddress, final List<String> toAddress, final List<String> ccAddress, final String subject,
                                              final String mailContent, final boolean isMailHtml) throws Exception {

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.setSubject(subject);
                helper.setFrom(fromAddress);
                helper.setTo(toAddress.toArray(new String[toAddress.size()]));

                if (ccAddress != null && ccAddress.size() > 0) {
                    helper.setCc(ccAddress.toArray(new String[ccAddress.size()]));
                }

                // use the true flag to indicate you need a multipart message
                helper.setText(mailContent, isMailHtml);
            }
        };
        try {
            mailSender.send(preparator);
            log.debug("EMail without attachment successfully sent !! \nFrom Addres: " + fromAddress + " To Address: " + toAddress + " CC Address: " + ccAddress + "\n Subject: " + subject);
            log.debug(subject);
        } catch (MailException ex) {
            log.error("Error Sending email: " + ex.getMessage());
            log.error("Sending mail failed for \nFrom Addres: " + fromAddress + " To Address: " + toAddress + " CC Address: " + ccAddress + "\n Subject: " + subject + " Content: " + mailContent + " isTemplateHtml: " + isMailHtml);
            throw new Exception("Error Sending Velocity Template mail. " + ex.getMessage(), ex);
        }

    }

}

/**
 * TODO Implement the functionality for TimesheetApp.
 *
 * @Override public void sendEmailNotification(long contractId, String actionPerformed) {
 * logger.debug("Sending email notification for :" + actionPerformed);
 * try {
 * EcasContract contract = ecasCommonService.getEcasContract(contractId);
 * String toEmail = StringUtils.EMPTY, emailSubject = StringUtils.EMPTY, templateName = StringUtils.EMPTY, staffEak = StringUtils.EMPTY;
 * String fromEmail = ecasCommonService.getEcasAppProperty(EcasConstants.APP_PROPERTY_FROM_EMAIL);
 * //Mapping object as per the template defined.
 * Map<String, Object> mailModel = new HashMap<>();
 * mailModel.put("contractKey", contract.getDsc_contract_id());
 * mailModel.put("sfaName", contract.getNme_sfa());
 * mailModel.put("entitycode", contract.getIdn_sfa());
 * mailModel.put("vendorname", contract.getEcasVendorDetails().getNme_vendor());
 * mailModel.put("contractSpan", contract.getNum_contract_span());
 * mailModel.put("contractStartDate", DateFormatUtils.format(contract.getDte_contract_start(), "MM/dd/yyyy"));
 * mailModel.put("contractEndDate", DateFormatUtils.format(contract.getDte_contract_end(), "MM/dd/yyyy"));
 * mailModel.put("contactCertifiedDate", DateFormatUtils.format(contract.getDte_contract_certified(), "MM/dd/yyyy"));
 * <p>
 * SNEARSUser contractCertifyingUser = snearsUserService.getUserForEAK(contract.getEak_user_certified());
 * <p>
 * switch (actionPerformed) {
 * case EcasConstants.CONTRACT_STATUS_CODE_CERTIFIED:
 * int countyId = contract.getIdn_county();
 * <p>
 * staffEak = ecasCommonDAO.getAssignedStaffEakForCountyCode(countyId);
 * if (staffEak != null && StringUtils.isNotBlank(staffEak)) {
 * SNEARSUser stateStaffAssignedForCounty = snearsUserService.getUserForEAK(staffEak);
 * toEmail = stateStaffAssignedForCounty.getUserBusinessEmail();
 * } else {
 * toEmail = ecasCommonService.getEcasAppProperty(EcasConstants.APP_PROPERTY_TO_EMAIL_WHEN_STAFF_NOTASSIGNED);
 * }
 * emailSubject = ecasCommonService.getEcasAppProperty(EcasConstants.APP_PROPERTY_CV_CERTIFICATION_EMAIL_SUBJECT);
 * mailModel.put("contactCertifiedUser", contractCertifyingUser.getUserFirstName() + "," + contractCertifyingUser.getUserLastName());
 * <p>
 * templateName = ecasCommonService.getEcasTimeBasedAppProperty(EcasConstants.APP_PROPERTY_CV_EMAIL_CERTIFIED_TEMPLATE);
 * mailService.sendTemplateMailWithoutAttachment(fromEmail, toEmail, null, emailSubject, mailModel, templateName, true);
 * break;
 * case EcasConstants.CONTRACT_STATUS_CODE_PREAPPROVED:
 * emailSubject = ecasCommonService.getEcasAppProperty(EcasConstants.APP_PROPERTY_CV_PREAPPROVE_EMAIL_SUBJECT);
 * <p>
 * templateName = ecasCommonService.getEcasTimeBasedAppProperty(EcasConstants.APP_PROPERTY_CV_EMAIL_PREAPPROVE_TEMPLATE);
 * toEmail = contractCertifyingUser.getUserBusinessEmail();
 * mailService.sendTemplateMailWithoutAttachment(fromEmail, toEmail, null, emailSubject, mailModel, templateName, true);
 * break;
 * case EcasConstants.CONTRACT_STATUS_CODE_NOTAPPROVED:
 * emailSubject = ecasCommonService.getEcasAppProperty(EcasConstants.APP_PROPERTY_CV_CERT_NOT_APPROVE_EMAIL_SUBJECT);
 * <p>
 * templateName = ecasCommonService.getEcasTimeBasedAppProperty(EcasConstants.APP_PROPERTY_CV_EMAIL_NOT_APPROVE_CERTIFICATION_TEMPLATE);
 * toEmail = contractCertifyingUser.getUserBusinessEmail();
 * mailService.sendTemplateMailWithoutAttachment(fromEmail, toEmail, null, emailSubject, mailModel, templateName, true);
 * break;
 * case EcasConstants.CONTRACT_STATUS_CODE_SIGNED:
 * <p>
 * staffEak = ecasCommonDAO.getAssignedStaffEakForCountyCode(contract.getIdn_county());
 * if (staffEak != null && StringUtils.isNotBlank(staffEak)) {
 * SNEARSUser stateStaffAssignedForCounty = snearsUserService.getUserForEAK(staffEak);
 * toEmail = stateStaffAssignedForCounty.getUserBusinessEmail();
 * } else {
 * toEmail = ecasCommonService.getEcasAppProperty(EcasConstants.APP_PROPERTY_TO_EMAIL_WHEN_STAFF_NOTASSIGNED);
 * }
 * emailSubject = ecasCommonService.getEcasAppProperty(EcasConstants.APP_PROPERTY_CV_SIGNED_EMAIL_SUBJECT);
 * mailModel.put("contactCertifiedUser", contractCertifyingUser.getUserFirstName() + "," + contractCertifyingUser.getUserLastName());
 * mailModel.put("ContractSignedDate", DateFormatUtils.format(contract.getDte_contract_signed(), "MM/dd/yyyy"));
 * <p>
 * templateName = ecasCommonService.getEcasTimeBasedAppProperty(EcasConstants.APP_PROPERTY_CV_EMAIL_SIGNED_TEMPLATE);
 * mailService.sendTemplateMailWithoutAttachment(fromEmail, toEmail, null, emailSubject, mailModel, templateName, true);
 * break;
 * case EcasConstants.CONTRACT_STATUS_CODE_APPROVED:
 * emailSubject = ecasCommonService.getEcasAppProperty(EcasConstants.APP_PROPERTY_CV_APPROVED_EMAIL_SUBJECT);
 * <p>
 * templateName = ecasCommonService.getEcasTimeBasedAppProperty(EcasConstants.APP_PROPERTY_CV_EMAIL_APPROVED_TEMPLATE);
 * toEmail = contractCertifyingUser.getUserBusinessEmail();
 * mailService.sendTemplateMailWithoutAttachment(fromEmail, toEmail, null, emailSubject, mailModel, templateName, true);
 * break;
 * case EcasConstants.ECAS_CV_SECTION_NAME_SIGNATURE:
 * emailSubject = ecasCommonService.getEcasAppProperty(EcasConstants.APP_PROPERTY_CV_SIGN_NOT_APPROVE_EMAIL_SUBJECT);
 * <p>
 * templateName = ecasCommonService.getEcasTimeBasedAppProperty(EcasConstants.APP_PROPERTY_CV_EMAIL_NOT_APPROVE_SIGN_TEMPLATE);
 * toEmail = contractCertifyingUser.getUserBusinessEmail();
 * mailService.sendTemplateMailWithoutAttachment(fromEmail, toEmail, null, emailSubject, mailModel, templateName, true);
 * break;
 * case EcasConstants.ECAS_CV_CERTIFY_CHARGES:
 * emailSubject = ecasCommonService.getEcasAppProperty(EcasConstants.APP_PROPERTY_CV_CERTIFY_CHARGES);
 * <p>
 * templateName = ecasCommonService.getEcasTimeBasedAppProperty(EcasConstants.APP_PROPERTY_CV_EMAIL_CONFIRM_CHARGES_TEMPLATE);
 * toEmail = contractCertifyingUser.getUserBusinessEmail();
 * mailService.sendTemplateMailWithoutAttachment(fromEmail, toEmail, null, emailSubject, mailModel, templateName, true);
 * break;
 * <p>
 * }
 * } catch (Exception ex) {
 * logger.error("Exception sending email notification for contract Id: " + contractId + " Action performed: " + actionPerformed + "\n" + ex);
 * //Not throwing exception to fail silently as this is only for notification and should not stop the workflow.
 * }
 * <p>
 * }
 */
