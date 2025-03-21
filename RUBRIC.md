# Critter Chronologer RUBRIC

[Rubric Link](https://review.udacity.com/#!/rubrics/2787/view)

[github link](https://github.com/JediPak/nd035-c3-data-stores-and-persistence-project-starter)

## Helpful Links

### Udacity

* [Lesson5Concept4 of Data Stores and Persistence- H2 - IGNORE NOT SUPPORTED](https://knowledge.udacity.com/questions/373316)

### Other


## Rubric Grading 

### Section 1: Connect Application and Unit Tests to Datasources
| STATUS | CRITERIA | MEETS SPECIFICATIONS |
| ------------- | ------------- | ------------- |
| | 1. Connect applications to external MySQL database  | `src/main/resources/application.properties` file contains entries specifying the datasource url and user credentials. |
| | 2. Connect unit tests to internal H2 database  | `src/test/resources/application.properties` file contains entries specifying the internal h2 url and credentials. |
| | 3. Initialize DataSources from within Spring | Tables are created in a `schema.sql` or the `application.properties` file specifies an initialization mode and ddl-auto. |

### Section 2: Design Data Layer
| STATUS | CRITERIA | MEETS SPECIFICATIONS |
| ------------- | ------------- | ------------- |
| | 1. Create Entities that represent the storage needs of the application | Each Entity should represent a single, coherent data type. This project will require Entities that represent multiple types of pets, both customer and employee users, and schedules that associate pets and employees. This will require at least three Entity classes and perhaps more, depending on which strategies for managing complex or polymorphic types are chosen.<br /><br />Relationships between Entities should be clear from the Entity design. Entities that contain references to multiple objects of the same type should represent that relationship with collection classes, not by packing multiple values into a single field, such as a delimited String or bit-packed integer.<br /><br />If polymorphic types are used, be sure to consider which table mapping strategy you wish to use and comment in the respective Entity classes why you’ve chosen a particular strategy. |
| | 2. Create components to access the data source | The application should either use the DAO pattern or Repository pattern to isolate access to your data source. You should have one DAO or Repository for each Entity you define and that component will expose CRUD operations to the rest of the program.<br /><br />A DAO or Repository can handle complex query operation, but should not combine multiple separate actions into a single call. |
| | 3. Choose appropriate data types | When considering data that can be represented by a variety of types, prefer choices that maximize clarity and limit the potential for invalid data.<br /><br /><ul><li>Date vs. DateTime vs. Time - Do not store date or time information unrelated to the requirements of that field.</li><li>Set vs. List - Select collections that match your constraints, such as uniqueness.</li><li>Enum vs. String constant vs. int constant - Where possible, prefer Enum representation in Java to maximize compiler assistance. SQL representation is up to you.</li></ul><br /><br />Additionally, consider both the current and long term needs of the data and choose data types that will be resilient to change without being inefficient.<br /><br /><ul><li>Long vs Int - Generally longs for ids and ints for everything else is fine, but always consider the ranges for your values</li><li>SQL Column width - Large widths don’t cost anything if unused, so avoid imposing narrow width restrictions on fields that could occasionally be long, like name. Restrict width on fields you know will always have a finite length and increase width on fields that you want to allow more than the default (255 for hibernate). </li></ul> |
| | 4. Set Transaction Boundaries | Individual SQL request are already transactional, but any part of your program that initiates a database request should start a transaction before taking steps that can cause failure. For most projects, a sensible transaction boundary will likely occur at the Service layer. You can begin transactions by annotating the methods @Transactional, or you can specify an entire class as @Transactional to mark all methods transactional.<br /><br />If you wish to use the EntityManager to manually start and end transactions you may, but be sure to minimize the scope of each method to limit the necessity.|
<br /><br />
### Section 3: Multilayer Architecture
| STATUS | CRITERIA | MEETS SPECIFICATIONS |
| ------------- | ------------- | ------------- |
| | 1. Transform Entity Data into DTOs | The output from your Data layer is in a format suitable for use by the rest of the application, but not necessarily the format you want to provide to consumers of your REST endpoints.<br /><br />For the purpose of this program, a number of pre-made DTOs have been provided to demonstrate the requirements of the front end without implying the structure of data on the back end. DTO needs will vary by consumer, but for the purpose of this program you will need to convert your Entity data back into the provided DTO format.<br /><br />This mapping should happen either in the DTO itself or in the Controller layer, so that the Service layer does not need to know anything about DTO structure. It should not happen in the Service layer. Feel free to use Spring utilities such as BeanUtils.copyProperties to facilitate copying properties of the same name between objects.<br /><br />If you’re feeling adventurous, check out the Stand Out Suggestions #2 and replace some of the DTOs with the original Entities annotated using JSONView and JSONIgnore. Many applications will create DTOs for large transformations but expose annotated Entities for scenarios where little or no transformation is necessary. Note that this approach may require updates to the unit tests. |
| | 2. Separate Domain Logic from Persistence Layer | Partition your logic into these layers:<br /><br /><ul><li>Service Layer retrieves information from one or more data sources. It can handle coordination between multiple data sources to solve multi-step problems</li><li>Data Layer modifies or returns Entities. It can join tables to aggregate data across multiple Entity types but should avoid performing multiple operations in a single request.</li></ul><br /><br />For example, if you wanted to modify an incoming Schedule request if it occurs on the same date as a Pet’s birthday, you would do this in the Service layer by first looking up the Pet and then modifying and saving the Schedule.<br /><br />However, if you wanted to find all pets who had an event scheduled on their birthday, this would make more sense as a single query, rather than having the Service layer request all Schedules and compare dates itself.<br /><br />Validation should occur in the Service layer rather than the Data layer, so rather than writing a query that will fail for invalid data, it is better to handle or throw an exception from the Service layer.<br /><br />Do not put any domain logic into the Controller layer. |
| | 3. DAO or Repository objects focus on their own Entities | Repositories should return objects of their own Entity type. For example, you would expect a “findPetsByOwner” query in the Pet Repository, rather than creating a method in a User or Customer Repository that looks up a customer then returns the Pets associated with it. <br /><br />While the Data layer can join and sometimes even modify other tables, the primary focus should be on the Entity it manages. In this project, every method that returns an Entity or List of Entities from the data layer should exist in the DAO or Repository of the same name as that Entity. |
<br /><br />
### Section 4: Workflow Functionality
| STATUS | CRITERIA | MEETS SPECIFICATIONS |
| ------------- | ------------- | ------------- |
| | 1. Write code that passes all unit tests | <ul><li>`testCreateCustomer`</li><li>`testCreateEmployee`</li><li>`testAddPetsToCustomer`</li><li>`testFindPetsByOwner`</li><li>`testFindOwnerByPet`</li><li>`testChangeEmployeeAvailability`</li><li>`testFindEmployeesByServiceAndTime`</li><li>`testSchedulePetsForServiceWithEmployee`</li><li>`testFindScheduleByEntities` |
| | 2. Write code that allows all requests in the included Postman collection to execute | All the requests in the Postman collection can be run and return correct information based on the data requested.<br /><br />`src/main/resource/Udacity.postman_collection.json`|

