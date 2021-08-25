---
layout: post
title: "Testing"
date: 2017-06-26
---


<p>The word “TEST” is derived from the latin word “Testum” meaning a pottery vessel used to measure or assess. Area of software testing is one of the key process areas of project life cycle in ensuring the quality of the software.</p>
<img src="{{site.baseurl}}/images/STLC-2.jpg" align="middle" height="250" width="500">
<p>There are various testing technique as follows,</p>
<ul> 
<li>Structural & Functional </li>
<li>Verification & Validation</li>
<li>Static & Dynamic</li>
<li>White-box & Black-box</li>
</ul>
<p>Different levels of testing :</p>
<ul> 
<li>Unit Testing</li>
<li>Integration  Testing</li>
<li>System  Testing</li>
<li>Acceptance  Testing</li>
<li>Black-box  Testing</li>
<li>White-box  Testing</li>
</ul>
<img src="{{site.baseurl}}/images/types_manual_testing.jpg" align="middle" height="500" width="500">
<p>The universe of testing automation can be neatly split into two predominant testing techniques known as black-box and white-box testing. Black-box testing is testing technique having no knowledge of the internal structure of system. Whereas, the focus of white-box testing is on the logic of implementation.</p>

<h4>Difference between Black-box and White-box testing</h4>
<img src="{{site.baseurl}}/images/black_vs_white.png" align="middle">
<table border= "1" >

 <tr>
    <th></th>
    <th>Black-box testing</th>
    <th>White-box testing</th>
   
 </tr>

  <tr>
    <td>Programming
        knowledge</td>
    <td>In this testing knowledge of programming is not necessarily essential.
                        White Box Testing</td>
    <td>In this form of testing knowledge of programming 
                 is must means it is essential</td>
  </tr>
  
  <tr>
    <td>Responsibilty</td>
    <td>Independent software testers</td>
    <td>Software developers</td>
  </tr>
  
  <tr>
    <td>Technically strong</td>
    <td>may or may not be technically sound.</td>
    <td> technically sound good</td>
  </tr>
  
   <tr>
    <td>Main focus</td>
    <td>Functionality of the system</td>
    <td> Structure means program/code of the system</td>
  </tr>
  
  <tr>
    <td>Requirement focus</td>
    <td>Focuses on what is performing/ carried out</td>
    <td> Focuses on how it is performing/ carried out</td>
  </tr>
  
   <tr>
    <td>Meaning</td>
    <td>Functional test or external test.</td>
    <td>Structural test or interior test</td>
  </tr>
  
</table>



<h2>Black-Box Testing</h2>

<p>Black box testing is the Software testing method which is used to test the software without knowing the internal structure of code or program. Basically software under test is called as “Black-Box”. While testing the tester only knows about the input and expected outputs of the software, they are not aware of how the software or application actually processes the input and outputs. The main purpose of the Black Box is to check whether the software is working as per user expectations.</p>

<img src="{{site.baseurl}}/images/black.png" align="middle" height="300" width="500">

<table border="1"> 

<tr>
<th>Test case ID</th> 
<th>Scenario</th>
<th>Data Value1</th>
<th>Data Value2</th>
<th>Expected Result</th>
<th>Test Result</th>
</tr> 

<tr>
<td>1</td> 
<td>As a User, I want to add activities so that I can monitor them over a period of time</td>
<td>Click on the add button &#8594; Enter values &#8594; add</td>
<td>Click on the add button &#8594; press back button</td>
<td>Data Value1: Display the activity in list
    <br>Data value 2: Come back to home page</td>
<td>Pass</td>
</tr> 

<tr>
<td>2</td> 
<td>As a User, I want some default categories so that I can group my activities accordingly</td>
<td>Click on add category &#8594; choose sports &#8594; add</td>
<td>Click on add &#8594; Display &#60; select category &#62; in add activity screen.</td>
<td>Data value1: Default category (sports) should be selected.
    <br>Data value2:Go to add activity screen &#8594; Display &#60; select category &#62; </td>
<td>Pass</td>
</tr> 

<tr>
<td>3</td> 
<td>As a User, I want to create categories so that I can have user-specific categories</td>
<td>Click on add &#8594;  Display &#60; select category &#62; in add activity screen.</td>
<td>Click on add category</td>
<td>Data value1: Go to add activity screen &#8594; Display &#60; select category &#60;
    <br>Data value2: Pop up add Category &#8594; enter Name</td>
<td>Pass</td>
</tr> 

<tr>
<td>4</td> 
<td>As a User, I want to input time and date when adding an activity so that I can have a Time stamp</td>
<td>Click on select Date &#8594; select date from calendar &#8594; add</td>
<td>View date for created activity in home screen</td>
<td>Data value1:  pop up calendar 
    <br>Data value2:  view Time stamp in Home screen for particular activity </td>
<td>Pass</td>
</tr> 

<tr>
<td>5</td> 
<td>As a User, I want to delete activities so </td>
<td>Long press on Activity in home screen</td>
<td>Click on delete</td>
<td>Data value1:  pop up delete 
    <br>Data value 2: Remove the activity selected from home screen</td>
<td>Pass</td>
</tr> 
</table>

<h2>White-Box Testing</h2>
<p>White Box Testing is a software testing method in which the internal structure/ design/ implementation of the item being tested is known to the tester. The tester chooses inputs to exercise paths through the code and determines the appropriate outputs.</p>
<p>A tester, usually a developer as well, studies the implementation code of a certain field on a webpage, determines all legal AND illegal inputs and verifies the outputs against the expected outcomes, which is also determined by studying the implementation code.<br>
White Box Testing is like the work of a mechanic who examines the engine to see why the car is not moving.</p>
<img src="{{site.baseurl}}/images/White-Box.jpg" align="middle">
<p>Test Case 1: showTimePickerDialog(View v) method displays a dialog where the user can pick the time.
In the app we have start time and end time options, so we call this function two times. Using a if condition on View 'v' we check whether the user clicked on Start Time button or the Stop Time button and then update the database accordingly.
We tested this method by passing resource id of button as an argument to the function instead of View object itself, just to make sure that the database is updated appropriately. And then we also tested the method by passing View object as an argument and the result was satisfactory.</p>
<br>
<img src="{{site.baseurl}}/images/showTimePickerDialog.PNG"  align="middle">
<br>
<p>Test Case 2: In the DatabaseHelper class, the CreateActivity() method is used to create a new activity when the user fills in the details and clicks on the Add button. The method uses the Activity class of the Model Package, where the schema of the database is defined. We tested this method by providing input from the console window and making sure that all the attributes are added to the database correctly.</p>
<br>
<img src="{{site.baseurl}}/images/CreateActivity.PNG"  align="middle">
<br>
<p>Test Case 3: The deleteActivity() method under the DatabaseHelper class is used to delete the activity entries from the database, we tested this method by passing ROW_ID as an argument to the method and we made sure that the expected rows are deleted not the other ones.</p>
<br>
<img src="{{site.baseurl}}/images/deleteActivity.png"  align="middle">

<p>Test Case 4: getActivities_filtered() method is used to fetch the queried rows from the database, so that the filtered activities can be displayed to the user. It takes from_date and to_date as inputs and it returns the Cursor object, which can then be used to display the list of activities to the user. We tested this method by providing different from and to dates. The test failed initially because we were not following the standard way of representing date (YYYYMMDD) and thus we were unable to sort the items properly. We then changed our date representation and obtained satisfactory results.</p>
<br>
<img src="{{site.baseurl}}/images/getActivities_filtered.png"  align="middle">

<p>Test Case 5: The onItemSelected() method in CreateActivity class is used to perform an action when the user selects an item on the spinner list. The initial test we performed on this method failed as we were passing the position of the item selected incorrectly, we realized this mistake after testing and corrected it.</p>
<br>
<img src="{{site.baseurl}}/images/spinner.PNG"  align="middle">
<p>White-box testing is performed early in the testing process, while black-box testing is applied during later stages. </p>
<img src="{{site.baseurl}}/images/code.jpg"  align="middle">
<h2>Summary of changes</h2>
<ul> 
<li>We added provision to filter activities</li>
<li>We added an option to sort activities</li>
<li>We added possibility to delete activities</li>
</ul>
<img src="{{site.baseurl}}/images/changes.png"  align="middle" height="300" width="500">
<p>THANKS FOR VISITING OUR BLOG,<br>Team BlueSky</p>
