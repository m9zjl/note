# lucene index

## lucene indexing uml

``` sequence
IndexWriter->DocumentWriter: addDocument>updateDocuemnt
DocumentWriter->DocuemntWriterPerThead: get PerThread from FlushControl
DocuemntWriterPerThead->DocConsumer: udpateDocument
DocConsumer->DefaultIndexingChain:processDocument
DefaultIndexingChain->PerField: peocessField
PerField->TermsHashPerField:invert
TermsHashPerField->FreqProxTermsWriterPerField:add-newTerms/addTerms
FreqProxTermsWriterPerField->TermsHashPerField: null
TermsHashPerField->PerField:
PerField->DefaultIndexingChain:
DefaultIndexingChain->DocConsumer:
DocConsumer->DocuemntWriterPerThead:
DocuemntWriterPerThead->DocumentWriter:
DocumentWriter->IndexWriter:updateDocuemnt
```
