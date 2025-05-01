package week6;

/**
 *   OSI
 *
 *   A -> layer 7, http, websocket, grpc
 *   P
 *   S
 *   T -> tcp , udp
 *   N -> ip
 *   D
 *   P
 *
 *   On-demand
 *   1. EC2
 *      VPC : CIDR block
 *            Private Subnet -> NAT gateway
 *            Public Subnet -> internet gateway
 *
 *      ASG : auto scaling group
 *      CloudWatch: log + alarm
 *
 *   2. Lambda(micro vm)
 *      def handler(event):
 *          res = event.get("xx")
 *          try:
 *              if xxx :
 *                  ...
 *              elif xx:
 *              else xx:
 *                  return {..}
 *          catch Exception as ex:
 *              logger.error()
 *              return {..}
 *
 *       problems:
 *       1. code size(?) / layer size max (250MB)
 *       2. longest running time is 15min
 *       3. cold start
 *
 *     AWS SAM
 *          write template.yaml -> lambda deployment script
 *          sam build
 *          sam local invoke --event ../folder/folder/xx.json
 *          sam deploy --config-env dev
 *
 *   3. LoadBalancer
 *      a. Application LB (layer7)
 *      b. Network LB (layer4)
 *      ..
 *   4. Zone, Region
 *      Region contains many zone
 *      Zone contains some data center
 *      Multi-AZ/Zone = deploy in diff data center
 *   5. Route53
 *      a. DNS
 *      b. load balance request cross regions
 *      c. health check (Head http method)
 *   6. VPC Endpoint
 *   7. SQS, SNS
 *   8. S3 (object storage)
 *      1. bucket + object key
 *      2. immutable object
 *      3. s3 pre signed url (get/put), timeout(5min/10s/30s)
 *      4. multi-part upload
 *            user ->  s3
 *                multipart upload
 *                <- id / key
 *                ->
 *                upload diff parts in diff request with key
 *                order number ?
 *                <- md5/ hash result
 *
 *                -> last request/complete request
 *                   key, finish status, [?] ??
 *      5. encryption
 *          s3 default encryption
 *          use KMS encrypt key
 *          upload encrypted data
 *          send key through the request
 *      6. Static Website
 *
 *
 *   9. S3 Glacier
 *  10. KMS
 *  11. CloudFront (CDN)
 *      edge location (data center in diff region/area)
 *
 *      user -> read from edge location -> read from CDN -> s3 / endpoint
 *  12. IAM
 *  13. Cognito
 *       1. User Pool (users)
 *       2. Generate ID token / Access Token
 *       ..
 *  14. AWS Glue (ETL)
 *       1. Data Catalog
 *       2. Work Flow
 *          Step1 -> Step2 -> Step3 .. -> S3
 *  15. SageMaker
 *       1. train data (s3)
 *       2. generate model -> s3
 *       3. deploy model from s3 to sagemaker endpoint (serverless)
 *  16. AWS Connect
 *      Transcribe
 *      Polly
 *      Lex
 *      Kinesis
 *      S3
 *  17. CloudFormation
 *  18. Cloud map
 *
 *
 *
 *    S3 <-  CDN  <-    browser    ---Route53 DNS
 *                       |
 *                     region us-east-1
 *                      |
 *                    API Gateway(rest/http)
 *                      |       \
 *                    ALB       Lambda
 *                     |
 *  Cloudwatch <-> ASG[EC2, EC2,EC2]
 *                     |
 *                    S3 -> S3 Glacier
 *
 *
 *
 *   elastic search / open search(serverless)
 *
 * *  * *  * *  * *  * *  * *  * *  * *
 *  associate developer / associate solution architecture
 *
 * *  * *  * *  * *  * *  * *  * *  * *
 * 1. send me your homework github link before 1:30pm cdt
 *    remote git(in properties folder) before commit / push your code to github
 * 2. take some screenshots and
 * 3. write read me
 *
 *
 */