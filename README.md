# s3

Understanding and using [S3 Object Storage](https://aws.amazon.com/de/s3/)

- Data organized in Buckets as Objects
- One single object can have a size of max 5TB
- Objects can have metadata (max 10?)
- Control data access
- Monitoring (Access, Activity)
- Flat structure
- Versioning
- Replication
- Different storage priceses
- Encryption
- Rentention


## Discoveries

- You can not filter objects by meta data
- You can list objects and filter them by prefix of the name
- You can use the name and create pseudo-hierarchical structures
- Amount of buckets and objects doesn't influence performance
- S3Select is to select documents and parsing it's content
