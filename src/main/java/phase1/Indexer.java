package phase1;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;

public class Indexer {
    private IndexWriter writer;

    // Constructor of Indexer class
    public Indexer() throws IOException {
        Directory index = new RAMDirectory();
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig();
        writer = new IndexWriter(index, config);
    }
    // To add Documents
    public void addDocument(String name, String address) throws IOException{
        Document document = new Document();
        document.add(new TextField("name", name, TextField.Store.YES));
        document.add(new TextField("address", address, TextField.Store.YES));
        writer.addDocument(document);
    }
    // Closing the writer
    public void close() throws IOException {
        this.writer.close();
    }

    public static void main(String[] args) {
        try {
            Indexer indexer = new Indexer();
            indexer.addDocument("Kharazmi", "Marve");
            indexer.addDocument("Nicola Tesla", "Serbia");
            indexer.addDocument("Kurt Godel", "Switzerland");
            indexer.addDocument("Bo Ali Sina", "Hamedan");
            indexer.addDocument("Cristiano Ronaldo", "Portugal");
            indexer.addDocument("Ronaldo Nazario", "Brazil");
            indexer.close();
        }catch (IOException e){
            System.out.print(e.getMessage());
        }
    }

}
