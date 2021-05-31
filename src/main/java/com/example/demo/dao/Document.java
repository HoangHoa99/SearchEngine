package com.example.demo.dao;

import java.time.LocalDate;

import javax.persistence.*;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.SortableField;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

import lombok.Data;

@Entity
@Table(name = "documents")
@Data
@Indexed
@AnalyzerDef(name = "documentanalyzer",
    tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
    filters = {
        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
        @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
            @Parameter(name = "language", value = "English")
        })
    }    
)
public class Document {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field
    @Column(name = "document_title")
    @Analyzer(definition = "documentanalyzer")
    private String documentTitle;

    @Field
    @Column(name = "document_des")
    @Analyzer(definition = "documentanalyzer")
    private String documentDes;

    @Field
    @Column(name = "document_source")
    private String documentSource;

    @Field(index = Index.NO, store = Store.NO, analyze = Analyze.NO)
    @SortableField
    private LocalDate dateCreate;

    @Field(index = Index.NO, store = Store.NO, analyze = Analyze.NO)
    @SortableField
    @Column(name = "review")
    private Integer review;

    @Field(name = "sortDownloaded", index = Index.NO, store = Store.NO, analyze = Analyze.NO)
    @SortableField(forField = "sortDownloaded")
    private Integer downloaded;
    
    @Field(index = Index.NO, store = Store.NO, analyze = Analyze.NO)
    @SortableField
    private Integer clickCount = 0;
}
