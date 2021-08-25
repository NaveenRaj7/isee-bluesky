---
layout: post
title: "ISEE 2017-Bluesky-System Design"
date: 2017-05-08  
---

<h2>System Design</h2>

<p>The design phase plays vital role in the development of a product. We have requirement specification which is a formal design structure but it is need of an hour to have architecture specification/detailed design of the requirements mentioned by user. The team meetings and discussion helped us to build final design structure according to the requirement, in the form of user-stories</p>

<p>In this System Design, we have designed the UML class diagram which will help us to develop the App. Our UML diagram has functional core which includes list of association, aggregation, operations, attributes</p>

<p>From the Design Model (UML diagram) program will be further elaborated. Design Model is more structured and more detailed.</p>

The interaction diagram is below,

<h2>Interaction Diagram</h2>
<img src="{{site.baseurl}}/images/InteractionDiagram.png" align="middle">

<p>Initially, Application will be installed by user. User will have option to create new Activities, categories and Projects. When user select to create new activity, a fresh screen to add activity will appear, in which, user will able to add title of activity, select category, project, time and date.
If desired category and project is not in the list, new category and project can be added by user.
As user saves this data, it will be stored in the database and will appear in home screen of the Application.</p>

<p>If user wants to add new projects or categories, they will able to create them directly in home screen too. It will be added into category and project list.</p>

<h2>Class Diagram</h2>
<img src="{{site.baseurl}}/images/ClassDiagram.png" align="middle">

<p>The classes Projects, Activity and Categories use DatabaseHelper to save, edit and delete data from the database.</p>

<h3>Create Activites:</h3>
<p>When the Activities are created they will be assigned to default projects and Categories or user can himself define new projects and categories also the user can select the start time and end time of the activity performed.</p>

<h3>Create Projects: </h3>
<p>To create a new project the user has to specify the project name. By default all the activities are assigned to a project named ‘Default’. If user wants to allot activity into a new project, he will able to create new one.</p>

<h3>Create Categories: </h3>
<p>User will be able to assign each activity to different categories. By default, some categories are included in the App. To add more categories in Application, user will have provision to add new categories.</p>


<h2>Behavioral Design Pattern</h2>
<img src="{{site.baseurl}}/images/DesignPattern.png" align="middle">

<p>Our system doesn't use any design pattern. One design pattern we could use is Behavioral design which is shown above.  Here, activities are the core entity that is being used by both Project and categories, where categories are again being inherited by project, so during the design phase we can refer to the behavioral design pattern and check for the attributes within each elements and how they are being used by other entities.</p>

<h2>Development Strategy</h2>
<p>Development phase was monitored through GitHub, where we placed our user stories as the integral design parameters for the software system, depending upon the relevance of each user story it was assigned to each of the members in group and moved to ‘in progress’. Once the design was completed each user stories that was assigned to each team member was reviewed during sprints within the presence of whole team.</p>


<p>That's all for now, We’ll be back soon with a new post on Implementation of our Design and also details of our progress in the upcoming sprints.</p>


Naveen Raj,<br>
Team Bluesky<br>
