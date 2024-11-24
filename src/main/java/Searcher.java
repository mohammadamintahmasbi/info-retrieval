import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Paths;

public class Searcher {

    public static void main(String[] args) throws Exception {
        // Open the index
        Directory index = FSDirectory.open(Paths.get("index"));
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        Analyzer analyzer = new StandardAnalyzer();

        // Create a query
        QueryParser parser = new QueryParser("fieldName", analyzer);
        Query query = parser.parse("your search query");

        // Execute the search
        TopDocs results = searcher.search(query, 10);

        // Display the results
        System.out.println("Total Results: " + results.totalHits);
        for (int i = 0; i < results.scoreDocs.length; i++) {
            int docId = results.scoreDocs[i].doc;
            System.out.println("Document found: " + searcher.doc(docId).get("fieldName"));
        }

        // Close the index reader
        reader.close();
    }
}