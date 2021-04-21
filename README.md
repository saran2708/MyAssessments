# MyAssessments

1. Download salarymgmt folder 
Crud and search operations on employee records from UI
2. open eclipse -> file ->import existing maven projects into workspace
3. Run the application.
4. To access applicaion use the url "http://localhost:8180/salarymgmt"
5. To upload csv file with employee id, employeeLogin, name and Salary, click on File upload link in home page
6. Upload csv file and click on submit
7. Successful page will be shown with the uploaded records in the database
8. To view all records click "View / Filter Records" link on home page
9. All the employee records will be displayed in the next page. This page also have options to filter the records 
based on minimum salary , maximum salary, sorting element (id or login or name or salary) and sorting order (asc/desc)
10. once enter he criteria click on search
11. Based on the criteria records will be displayed in the same page below. limit for number records is set as "3" so first page qill display only
3 records.To view more than 3 records "Next" button can be used to navigate.
12. To view records from previous page "Previous" button can be used in this page.
13. To edit each employee record click on the hyperlink on employee id (in View / Filter Records page) it will show employee page with details. 
14. Click on update button to update the changes.
15. To delete the employee records, click on hyperlink on delete button (in View / Filter Records page).

Crud and search operations on employee records from Rest client
1. Use postman to test the crud and search operations
2. To upload csv file select "POST" and URL "http://localhost:8180/salarymgmt/users/upload" , select form-data-> enter key as file and choose type as file->
upload csv file in content and click on send
3. To retrieve all uploaded details, select "GET" -> URL "http://localhost:8180/salarymgmt/getUsers" and click send to retrieve all employee details
4. To retrieve employee records based on criteria , select "GET" -> URL "http://localhost:8180/salarymgmt/users?minSalary=0&maxSalary=4000&offset=0&limit=30&sort=-name"
and click send to retrieve the employee records based on criteria.
5. To create employee record, select "POST" ->URL "http://localhost:8180/salarymgmt/users/create" -> select headers ->add key as "Content-type" and value as "application/json"
->select body -> choose raw -> and paste json  {"id": "e0011","login": "Emplogin27","name": "Arminius","salary": 34.23} in the body -> click send. this new record will be saved in db.
6. To retrieve the employee record by id select "GET" -> URL "http://localhost:8180/salarymgmt/users/e0011" -> click send to view the result in response.
7. To update the employee record by id select "PATCH" -> URL "http://localhost:8180/salarymgmt/users/update/e0011" -> select headers ->add key as "Content-type" and value as "application/json"
->select body -> choose raw -> and paste json  {"id": "e0011","login": "Emplogin27","name": "Arminius Dev","salary": 3400.23} in the body -> click send. Employee record e0011 will be updated
in db.
8. To delete record select "DELETE" -> URL "http://localhost:8180/salarymgmt/users/delete/e0011" and click send. The employee record "e0011" will be deleted from DB.

