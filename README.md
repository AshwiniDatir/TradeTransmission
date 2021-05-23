# TradeTransmission

##### Steps to run the code <H6>
  - Downlaod the repo.
  - Import the Existing maven project
  - Right click on project->Build project.

##### Steps to execute the code <H6>
  - Execute TradeStoreTest.java with Junit Test.


###### PROBLEM STATEMENT <h6> 
  There is a scenario where thousands of trades are flowing into one store, assume any way of transmission of trades. We need to create a one trade store, which stores the trade in the following order.
  


  Trade Id  | version| Counter-Party Id| Book-Id| Maturity Date| Created Date| Expired
------------- | -------------| -------------| -------------| -------------| -------------| -------------
Tl  | 1| CP-1| B1| 20/05/2020| <today date>| N
T2  | 2| CP-2| B1| 20/05/2021| <today date>| N
T2  | 1| CP-1| B1| 20/05/2021| 20/05/2021| N
T2  | 3| CP-3| B2| 20/05/2014| <today date>| Y




###### There are couples of validation, we need to provide in the above assignment <h6> 
- During transmission if the lower version is being received by the store it will reject the trade and throw an exception. If the version is same it will override the existing record.
- Store should not allow the trade which has less maturity date then today date.
- Store should automatically update the expire flag if in a store the trade crosses the maturity date.

  
##### Built information <h6>
- Java Maven project.
- Java Version Used:-JDK 1.8,JRE 1.8
- Junt Version Used- 5.7.1

 ##### Built information <h6>
1. Validate if 1st Trade is added.
2. Validate if Version is high the list will be updated.
3. Validate if Version is low the trade will be rejected.
4. Validate if Version is same the list will be updated.
5. Validate if maturity Date is greater than todays date the trade is added.
6. Validate if maturity Date is lower than todays date the Trade will not be added.
7. Validate if Version is Same and date is lower the trade is not updated.
8. Validate if Maturity Date is Same as Todays Date the list will be added.
9. Validate if version is high but maturity date is low the trade will be rejected.
10. Validate If Maturity Date is Expired it will update the Expired Flag
11. Validate with T1	1	CP-1	B1	20/05/2020	<today date>	N
12. Validate With T2	2	CP-2	B1	20/05/2021	<today date>	N
13. Validate With T2	1	CP-1	B1	20/05/2021	14/03/2015	N
14. Validate Expired T3	3	CP-3	B2	20/05/2014	<today date>	Y
  
  
###### Test Cases Output <H6>
- All test Case Passed.

