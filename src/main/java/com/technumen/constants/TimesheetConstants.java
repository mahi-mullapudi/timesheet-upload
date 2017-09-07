package com.technumen.constants;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@Component
public class TimesheetConstants implements Serializable {

    //Enumeration which maintain the timesheet status
    public enum Status {
        SAVED,
        SUBMITTED,
        APPROVED,
        REJECTED
    }

    public static final String SALT = "technumen";

    // All anticipated document types.
    public static final Map<String, String> TIMESHEET_FILE_EXTENSION_MAP = ImmutableMap.<String, String>builder()
            //Images
            .put("bmp", "image/bmp")
            .put("jpg", "image/jpeg")
            .put("jpeg", "image/jpeg")
            .put("png", "image/png")
            .put("tiff", "image/tiff")
            //pdf
            .put("pdf", "application/pdf")
            //outlook email
            .build();

    public static final String INIT_VECTOR_RANDOM_KEY = "RandomInitVector"; // 16 bytes IV any random String with 16 characters can be used.

    //TODO Implement the code for uploading file.
    /*
    @Override
    public String saveEcasContractDocument(long contractId, long refdataId, String sectionGroupCode, String fileName,
            String description, MultipartFile document, String userEak) throws Exception {
        logger.debug("In saveEcasContractDocument for Contract Id:" + contractId);
        Connection conn = null;
        CallableStatement callableStatement = null;
        String query = EcasQueryConstants.insertEcasDocument;
        String rowId = StringUtils.EMPTY;
        try {
            conn = ConnectionHandler.getConnection();
            InputStream blob = document.getInputStream();
            long docId = getSequenceNumber();
            callableStatement = conn.prepareCall(query);
            callableStatement.registerOutParameter(9, Types.VARCHAR);
            callableStatement.setLong(1, docId);
            callableStatement.setLong(2, contractId);
            callableStatement.setLong(3, refdataId);
            callableStatement.setString(4, sectionGroupCode);
            callableStatement.setString(5, fileName);
            callableStatement.setString(6, description);
            callableStatement.setBinaryStream(7, blob, blob.available());
            callableStatement.setString(8, userEak);
            int res = callableStatement.executeUpdate();
            if (res > 0) {
                rowId = callableStatement.getString(9);
                logger.debug("Inserted Row Id >>>> " + rowId + " for contract id :" + contractId);
            }
        } catch (IOException exc) {
            logger.error("IO Exception while Saving Documents for the Contract Id: " + contractId + ":\n" + exc);
            throw exc;
        } catch (Exception ex) {
            logger.error("Exception while Saving Documents for the Contract Id: " + contractId + ":\n" + ex);
            throw ex;
        } finally {
            ConnectionHandler.close(callableStatement);
            ConnectionHandler.close(conn);
        }
        return encodeRowId(rowId);
    }

    @Override
    public EcasContractDoc getEcasDocumentByRowId(String rowId) throws Exception {
        rowId = decodeRowId(rowId);
        Connection conn = null;
        String query = EcasQueryConstants.getEcasDocumentByRowId;
        EcasContractDoc contractDoc = null;
        try {
            conn = ConnectionHandler.getConnection();
            logger.debug("\n In getEcasDocumentByRowId");
            QueryRunner run = new QueryRunner();
            ResultSetHandler<EcasContractDoc> handler = new BeanHandler<>(EcasContractDoc.class);
            contractDoc = run.query(conn, query, handler, rowId);
            contractDoc.setRowid(encodeRowId(rowId));
        } catch (Exception exc) {
            logger.error("Exception while retrieving Ecas document from db for the row id : " + rowId + "\n" + exc);
            throw exc;
        } finally {
            ConnectionHandler.close(conn);
        }
        return contractDoc;
    }
     */

}
