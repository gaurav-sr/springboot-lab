### Overview ###
* This application delivers a basic gift exchange functionality.
* The core GiftAssignment logic is implemented in GiftAssignUsingShuffle class. This class using Collections shuffle method to shuffle the members.
* There is another implementation to assign members and recipient in GiftAssignUsingRandom. This class doesn't useds Collections.shuffle() but a Random number generator logic. The logic needs some changes as it was found that for some run of text cases the logic would go into infinite loop.
* The GiftExchangeController is the class that implements the Rest APIs. 
* The GiftExchangeIntegrationTest is integration test.
* The logic to test "assign gift to one member by another member once in 3 years" has been tested only through unit test. TestGiftAssignUsingShuffle.testAssignment()
* Some improvements that can be done
  * Handle scenarios where a member could not be assigned to any one
  * Swagger file to expose Rest API contract.
  * More testing to test the assignmeent and then subsequent logic refinement.
  * Saving the gift assignment in a table. 
