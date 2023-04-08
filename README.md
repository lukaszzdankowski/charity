<h2>Overview:</h2>
This web application enables making donations for registered users. It includes 3 different user roles:
<ul>
<li>User - can make a donation</li>
<li>Courier - can receive donation from User and deliver it to target Institution</li>
<li>Admin - maintains the system</li>
</ul>
charity is a project for educational purpose and it is still beeing improved.

<h2>Technologies used:</h2>
<ul>
<li>Java</li>
<li>Maven</li>
<li>Spring Boot</li>
<li>Spring Security</li>
<li>JPA</li>
<li>MySQL</li>
<li>SQL / JPQL / JPA methods</li>
<li>JSP</li>
<li>JavaScript</li>
<li>Lombok</li>
<li>JUnit 5</li>
</ul>

<h2>Completed features of charity:</h2>
✅ Added Institution, Category and Donation entities.<br>
✅ Added User and Role entities.<br>
✅ Added ability to register new User and login for Users with active accounts via Spring Security.<br>
✅ Added authorization via SecurityFilterChain.<br>
✅ Added homepage routing for authenticated User depending on User's role via AuthenticationSuccessHandler.<br>
✅ Added e-mail delivered token mechanism for account activation and password reset.<br>
✅ Completed a life cycle of donation from been Reported by User, Received and Delivered by Courier, to been Archived by Admin.<br>
✅ On "develop_test" branch added some unit tests for DonationRepository and DonationService
