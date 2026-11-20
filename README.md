# CICD-1 Coding Test — Review, Improve and Reflect

**Student ID:** G00467621  
**Examination group:** B (OpenFeign):  
**Original examination pull-request URL (`exam-work`):** https://github.com/SethATU/CICD_G00467621/pull/1  
**New review repository URL:** https://github.com/SethATU/CICD_Exam_Self_Assesment

## 1. My original examination self-assessment

### Task 1 — /40
**My mark:** 20/40  
**Evidence from the original code (files, methods, working or missing requirements):**

*Positive Marks*
- model is created correctly with the getters and setters correct
- most validation being correct and status is initally set to active

*Negative Marks*
- dto requests and responses not being correct at all, the request is 1/4 correct but their is no proper response 

### Task 2 — /40
**My mark:** 20/40  
**Evidence from the original code:** 

*Positive Marks*
- POST /api/bookings has a correct path to create and save a booking to the database
- FerignClient is properly setup in the model and in the client to connect with the other service
- sutable HTTP responses are setup for 201, 400 and 404

*Negative Marks*
- Ferign client dosent call the booking service
- the attencance count is never checked
- freign client calls /booking/{id} and returns a Booking, but it should call /api/rooms/{id} and return a room with id, name and capacity

### Task 3 — /20  
**My mark:** 15/20  
**Evidence from the original code:**  

*Positive Marks*
- findById("id") reads using the local database not from feign
- 404 not found and correct stats returned if id not found
- booking not found message attached

*Negative Marks*
- getById returns the Booking entity instead of response DTO
- getBookings(@Param("id") Long id) is unused 

**Total self-assessed original examination mark:** 55/100

## 2. Corrections and improvements in my new project

**Problem and correction 1:**  
*Problem:* 
- Client called /booking/{id} and returned a Booking and BookingService never called it and atendance count accepted even if it was more than the room could hold

*Correction:* 
- RoomClient now calls /api/rooms/{id} and returns RoomDto and createBooking calls it, rejects attendeeCount if its over capacity and getRoom returns a 404 for rooms it dosent know

*Why it Worked:*
- The Feign call returns the rooms capacity, and the booking is only saved after the check passes

**Problem and correction 2:**  
*Problem:*
- The validation was on the Booking entity with no @Valid in the controller and @Min/@Max check numbers not text length and @NotEmpty allows a blank name

*Correction:*
- I moved the validation to CreateBookingRequest @NotBlank and @Size(min = 2, max = 60) on organiserName and @NotNull and @Positive on roomId and attendeeCount i added @Valid in createBooking

*Why it Worked:*
- Validation runs when the request is sent and before the service is called so bad input gets a 400 and never reaches the database

**Problem and correction 3 (optional):**  
*Problem:*
- The controller returned the Booking entity and the request let the user set id and status

*Correction:*
- I added BookingResponse and toResponse() in BookingService and I removed id and status from the request and the service sets status to true and changed the path to /api/bookings

*Why it Worked:*
- The server controls id and status, and the JSON binds to the right endpoint

## 3. API testing and evidence

**Test 1 — successful booking request:**  
- Request / input:  { "organiserName": "Sean", "attendeeCount": 18, "roomId": 1 }
- Expected: 201 Created, Status = true
- Actual HTTP status / body: 201 { "id": 1, "organiserName": "Sean", "attendeeCount": 18, "roomId": 1, "status": true }
- Pass / fail: Pass

**Test 2 — invalid input or excessive attendee count:**  
- Request / input: { "roomId": 1, "organiserName": "Jason", "attendeeCount": 30 }
- Expected: 400 Bad request
- Actual HTTP status / body: 400 { "timestamp": "2026-11-20T13:40:42.327Z", "status": 400, "error": "Bad Request", "path": "/api/bookings" }  
- Pass / fail: Pass

**Test 3 — missing booking or room:**  
- Request / input: { "roomId": 99, "organiserName": "Brad", "attendeeCount": 5 }
- Expected: 404 Not found
- Actual HTTP status / body: 404 { "timestamp": "2026-11-20T13:45:24.526Z", "status": 404, "error": "Not Found", "path": "/api/bookings" }
- Pass / fail: Pass

**Test 4 — requirement specific to my group:**  
- Request / input: GET api/booking/{1}
- Expected: 200 ok
- Actual HTTP status / body: 200 { "id": 1, "organiserName": "Sean", "attendeeCount": 18, "roomId": 1, "status": true }  
- Pass / fail: Pass

## 4. Reflection

**What did I understand during the examination but struggle to implement?**  
- I understood the flow (controller / service / repository) and that feign was needed to get the room
- I struggled to connect get each to work together in time
- I wrote the freign client but never called it and pointed it at the wrong endpoint
- I put validation on the entity 

**Which Spring Boot, JPA, validation, JPQL or OpenFeign concept became clearer after correcting my code? Give a specific example.**  
- Validation: @Min/@Max check numbers not text length
- @Size(min = 2, max = 60) and @NotBlank is correct for a string
- Validation only runs with @Valid and Ints lets @NotNull catch a missing attendeeCount
- Feign client is just an interface and the call happens when the service uses it

**What will I do differently in my next coding test or API development task?**  
- Copy names and paths specified in the exam paper
- Build and test one task at a time
- Test with the example JSON early
- Delete unused code


## Review lab assessment — 20 marks

- Honest original self-assessment with code evidence: **4 marks**
- Explanation of mistakes and Spring Boot concepts: **6 marks**
- Corrections in the new application: **4 marks**
- API testing and demonstration of understanding: **4 marks**
- Reflection and future improvement: **2 marks**

**Total: 20 marks.** Simply submitting a working solution does not demonstrate the learning required by this review.

## Proposed mark recovery — only if confirmed for CICD-1

If the same approach as OOP is adopted, the review could recover **up to 30% of marks lost** in the original examination, scaled by the review-lab mark.

**Marks recovered = (100 − official original exam mark) × 0.30 × (review lab mark ÷ 20)**

Example: exam **40/100** and review **16/20** → **14.4 recovered marks**, revised exam **54.4/100**. This is **not an approved CICD-1 policy unless confirmed by the lecturer**. The original official exam mark—not your self-awarded grade—is used in the calculation.

## Submission

Commit and push the **new review repository** with the completed README, corrected Spring Boot code and API-test evidence. Submit its GitHub URL on Moodle. **Do not push any changes to the original examination repository or `exam-work` pull request.**
