package phase1;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;

public class Searcher {
    private IndexSearcher searcher;

    public Searcher(Directory index) throws IOException {
        this.searcher = new IndexSearcher(DirectoryReader.open(index));
    }

    public void search(String querySrt) throws Exception{
        QueryParser parser = new QueryParser("name", new StandardAnalyzer());
        Query query = parser.parse(querySrt);
        TopDocs results = searcher.search(query, 10);
        for(ScoreDoc scoreDoc : results.scoreDocs){
            Document doc = searcher.doc(scoreDoc.doc);
            System.out.print("Found: " + doc.get("name") + ", Address: " + doc.get("address"));
        }
    }

    public static void main(String[] args) {
        try {
            Directory index = new RAMDirectory();
            Indexer indexer = new Indexer(index);
            indexer.addDocument("Kharazmi", "Marve");
            indexer.addDocument("Nicola Tesla", "Serbia");
            indexer.addDocument("Kurt Godel", "Switzerland");
            indexer.addDocument("Bo Ali Sina", "Hamedan");
            indexer.addDocument("Cristiano Ronaldo", "Portugal");
            indexer.addDocument("Ronaldo Nazario", "Brazil");
            indexer.close();
            Searcher searcher1 = new Searcher(index);
            searcher1.search("Nicola");
        }catch (Exception e){
            System.out.print(e.getMessage());
        }
    }
}
