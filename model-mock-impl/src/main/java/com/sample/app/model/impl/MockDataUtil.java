package com.sample.app.model.impl;

import com.sample.app.model.client.AddressDetail;
import com.sample.app.model.client.ClientDetail;
import com.sample.app.model.client.CodeOption;
import com.sample.app.model.client.DocumentContent;
import com.sample.app.model.client.DocumentDetail;
import com.sample.app.model.client.StateType;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.io.IOUtils;

/**
 * Mock implementation for testing.
 */
public final class MockDataUtil {

	private static final AtomicInteger CLIENT_IDS = new AtomicInteger();
	private static final Map<String, ClientDetail> CLIENTS = new HashMap<>();
	private static final Map<String, List<DocumentDetail>> CLIENT_DOCUMENTS = new HashMap<>();
	private static final Map<String, DocumentDetail> DOCUMENTS = new HashMap<>();
	private static final Map<String, List<CodeOption>> TABLES = new HashMap<>();

	static {
		resetData();
	}

	/**
	 * Private constructor.
	 */
	private MockDataUtil() {
		// Do nothing
	}

	/**
	 * Reset the mock data.
	 */
	public static void resetData() {
		setupClients();
		setupTables();
	}

	/**
	 * @return the list of available table names
	 */
	public static List<String> retrieveTableNames() {
		return new ArrayList<>(TABLES.keySet());
	}

	/**
	 * @param table the table name
	 * @return the code options for the table
	 */
	public static List<CodeOption> retrieveTableCodes(final String table) {
		List<CodeOption> options = TABLES.get(table);
		return options;
	}

	/**
	 *
	 * @param search the search criteria
	 * @return the matching clients
	 */
	public static List<ClientDetail> searchClients(final String search) {

		List<ClientDetail> clients = new ArrayList<>();

		for (ClientDetail detail : CLIENTS.values()) {
			// Basic search
			if (isMatch(search, detail.getName())) {
				clients.add(detail);
			}
		}

		return clients;
	}

	/**
	 *
	 * @param clientId the client id to retrieve
	 * @return the client details
	 */
	public static ClientDetail retrieveClient(final String clientId) {
		return CLIENTS.get(clientId);
	}

	/**
	 *
	 * @param detail the client details to create
	 * @return the created client
	 */
	public static ClientDetail createClient(final ClientDetail detail) {
		String id = "ORG" + CLIENT_IDS.getAndIncrement();
		detail.setClientId(id);
		CLIENTS.put(id, detail);
		return detail;
	}

	/**
	 * @param detail the updated client details
	 * @return the client details
	 */
	public static ClientDetail updateClient(final ClientDetail detail) {
		String key = detail.getClientId();
		CLIENTS.put(key, detail);
		return detail;
	}

	/**
	 *
	 * @param clientId the client id to delete
	 */
	public static void deleteClient(final String clientId) {
		CLIENTS.remove(clientId);
		// TODO Remove documents as well
	}

	/**
	 * @param clientId the client id to retrieve documents for
	 * @return the list of client documents
	 */
	public static List<DocumentDetail> getOrCreateClientDocuments(final String clientId) {
		// Build mock list of document details
		List<DocumentDetail> docs = CLIENT_DOCUMENTS.get(clientId);
		if (docs == null) {
			docs = createDocuments(clientId);
			CLIENT_DOCUMENTS.put(clientId, docs);
			for (DocumentDetail doc : docs) {
				DOCUMENTS.put(doc.getDocumentId(), doc);
			}
		}
		return docs;
	}

	/**
	 * @param documentId the document id to retrieve
	 * @return the document details
	 */
	public static DocumentDetail retrieveDocument(final String documentId) {
		return DOCUMENTS.get(documentId);
	}

	/**
	 *
	 * @param doc the document content to retrieve
	 * @return the document content
	 */
	public static DocumentContent retrieveContent(final DocumentDetail doc) {
		byte[] bytes = getDocumentBytes(doc);
		String mime = getDocumentMimeType(doc);
		return new DocumentContent(doc.getDocumentId(), bytes, doc.getResourcePath(), mime);
	}

	private static void setupClients() {

		CLIENT_IDS.set(1);
		CLIENTS.clear();
		CLIENT_DOCUMENTS.clear();
		DOCUMENTS.clear();

		for (int i = 1; i < 10; i++) {
			ClientDetail client = createOrganisation(CLIENT_IDS.getAndIncrement());
			CLIENTS.put(client.getClientId(), client);
		}
	}

	private static void setupTables() {

		TABLES.clear();

		// Country
		List<CodeOption> options = new ArrayList<>();
		options.add(new CodeOption("A", "Australia"));
		options.add(new CodeOption("NZ", "New Zealand"));
		options.add(new CodeOption("UK", "United Kingdom"));
		TABLES.put("country", options);

		// Currency
		options = new ArrayList<>();
		options.add(new CodeOption("AUD", "Australia Dollar"));
		options.add(new CodeOption("GBP", "British Pound"));
		options.add(new CodeOption("USD", "US Dollar"));
		TABLES.put("currency", options);
	}

	private static ClientDetail createOrganisation(final int idx) {
		ClientDetail detail = new ClientDetail();
		detail.setClientId("ORG" + idx);
		detail.setEmail("eg@example.com");
		detail.setAddress(createAddress(idx));
		detail.setAbn("ABN" + idx);
		detail.setName("Name" + idx);
		return detail;
	}

	private static AddressDetail createAddress(final int idx) {
		AddressDetail detail = new AddressDetail();
		detail.setCountryCode("A");
		detail.setPostcode("2000");
		detail.setState(StateType.TAS);
		detail.setStreet("street" + idx);
		detail.setSuburb("suburb" + idx);
		return detail;
	}

	private static List<DocumentDetail> createDocuments(final String clientId) {
		// Build mock list of document details
		List<DocumentDetail> docs = new ArrayList<>();
		int idx = 0;
		for (String name : new String[]{"Einstein", "Bohr", "Maxwell", "Curie", "Schrodinger", "Feynman", "Young", "Roentgen"}) {
			DocumentDetail doc = createImageDocument(clientId, idx++, name);
			docs.add(doc);
		}
		for (String name : new String[]{"document1", "document2"}) {
			DocumentDetail doc = createWordDocument(clientId, idx++, name);
			docs.add(doc);
		}
		for (String name : new String[]{"sample1", "sample2"}) {
			DocumentDetail doc = createPdfDocument(clientId, idx++, name);
			docs.add(doc);
		}
		return docs;
	}

	private static DocumentDetail createImageDocument(final String clientId, final int idx, final String name) {
		return new DocumentDetail(createDocId(clientId, idx), name, createDate(idx), "/sample/images/" + name + ".jpg");
	}

	private static DocumentDetail createPdfDocument(final String clientId, final int idx, final String name) {
		return new DocumentDetail(createDocId(clientId, idx), name, createDate(idx), "/sample/docs/" + name + ".pdf");
	}

	private static DocumentDetail createWordDocument(final String clientId, final int idx, final String name) {
		return new DocumentDetail(createDocId(clientId, idx), name, createDate(idx), "/sample/docs/" + name + ".docx");
	}

	private static String createDocId(final String clientId, final int idx) {
		return clientId + "-doc-" + new DecimalFormat("000").format(idx);
	}

	private static Date createDate(final int idx) {
		int yr = 2010 - (idx % 4);
		int mth = 12 - (idx % 3);
		int day = 28 - (idx % 7);
		Calendar dt = Calendar.getInstance();
		dt.set(yr, mth, day);
		return dt.getTime();
	}

	private static byte[] getDocumentBytes(final DocumentDetail doc) {
		try (InputStream stream = MockDataUtil.class.getResourceAsStream(doc.getResourcePath())) {
			return IOUtils.toByteArray(stream);
		} catch (Exception e) {
			throw new IllegalStateException("Error loading resource." + e.getMessage(), e);
		}
	}

	private static String getDocumentMimeType(final DocumentDetail doc) {
		String resource = doc.getResourcePath();
		// Just the MIME Types for the MOCK Data
		if (resource.endsWith("jpg")) {
			return "image/jpg";
		} else if (resource.endsWith("pdf")) {
			return "application/pdf";
		} else if (resource.endsWith("docx")) {
			return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		} else {
			return "";
		}
	}

	private static boolean isMatch(final String search, final String value) {
		if (search == null || search.isEmpty()) {
			return true;
		}
		return value.toLowerCase().contains(search.toLowerCase());
	}

}
