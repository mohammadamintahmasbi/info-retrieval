import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.nio.file.Paths;

public class Indexer {

    public static void main(String[] args) throws Exception {
        // Create a new index
        Directory index = FSDirectory.open(Paths.get("index"));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(index, config);

        // Add documents to the index
        Document doc = new Document();
        doc.add(new Field("fieldName", "document content", Field.Store.YES, Field.Index.ANALYZED));
        writer.addDocument(doc);

        // Close the index writer
        writer.close();
    }
}