##PhylogeneticWebsite

PhylogeneticWebsite is a gene-analysis tool that includes
Subtree Extraction and Subsequence Map Extraction.

##Tips

1. application.properties:

    uploadPath: set directory of temp uploaded files
    
    IPLimitPeriod: set period of each ip access(minutes)
    
    IPLimitInPeriod: set access times in IPLimitPeriod minutes
    
    IPLimitPerDay: set total access times of each ip in a day
    
    ServerLimitPerDay: set total access times of the server in a day
    
    CleanupPeriod: set auto cleanup period(days)

    treFilePhyloAnalysis, sciCommonNameMappingFile, outTreeFile, subTreeImageFile
    
    allTaxaNameFile, treeFile, seqIDFile, phySeqFile
    
    Other keys are not used just for future work

2. AllBird.NewNames.7000Taxa.phy.zip:

    As the limitation of single file size in Github, please
    find this file and decompress it in the some directory
