# Full Java Alchemist

## What do we expect?
We expect that the amount of effort to do any of these exercises is in the range of about 2-3 hours of actual work. We also understand that your time is valuable, and in anyone's busy schedule that constitutes a fairly substantial chunk of time, so we really appreciate any effort you put into helping us build a solid team.

## What we are looking for?
Keep it simple. Really. 2-3 hours isn't a lot of time and we really don't want you spending too much more time on it than that.

> **Treat it like production code.** But Keep it simple.

That is, develop your software in the same way that you would for any code that is intended to be deployed to production. These may be toy exercises, but we really would like to get an idea of how you build code on a day-to-day basis.

## How to submit?
You can do this however you see fit - you can email us a tarball, a pointer to download your code from somewhere or just a link to a source control repository. Make sure your submission includes a small README, documenting any assumptions, simplifications and/or choices you made, as well as a short description of how to run the code and tests. Finally, to help us review your code, please split your commit history in sensible chunks (at least separate the initially provided code from your personal additions).

## The Interview
After you submit your code, we will contact you to discuss and arrange an in-person interview with some of the team. The interview will cover a wide range of technical and social aspects relevant to working at OnGres, but importantly for this exercise: we will also take the opportunity to step through your submitted code with you.

## The Exercise
The [Sakila](https://www.jooq.org/sakila) database example model a DVD rental store, and we want to play with it.

Check the [Use of Docker image](#use-of-docker-image) section to have a basic understanding of running it.

The main idea is to create a tool to manage a hypothetical DVD Rental Store, and you can implement it with a REST API or a CLI (or both if it fits your time).

You can use any framework you prefer to build REST API, here are some examples: Dropwizard (JAX-RS), Micronaut, Spring Boot... Or you can use these libraries to build the CLI: Picocli, jopts, commons CLI.

* If you choose to build a REST API, the response must be a JSON.
* If you choose to build a CLI, the response must be a YAML.
* To read from the database you should use “pure” JDBC, without frameworks this time.

### Part 1
We would like to generate reports using the API REST (or CLI) with the following information:

  * Number of Clients by the requested Country (and optionally by City too)
  * Films by requested Actor, with a filter by Category.
    * Return the following information:  Actor First Name, Actor Last Name, Film Title, Description, Category Name. 
    * The filter by category should not be in the query (hint: you can use the Java Stream API).


### Part 2 
- **Rent a DVD**

    Supposing you have the `customer_id` (for example, 473), the staff name `Jon Stephens` and the DVD title `HOBBIT ALIEN` to rent it. 

    First, confirm that the requested DVD is available in the inventory, you can use the SQL function `SELECT INVENTORY_IN_STOCK(inventory_id);`
    Regist the rent with an insert into `rental` table. 
    
    You may need to check whether the customer has an outstanding balance before processing the rental and that the given inventory item is in stock. Fot that, you have to get the balance with the SQL function `get_customer_balance(customer_id, LOCALTIMESTAMP);`, and then, register the balance obtained and the new `rental_id` into the `payment` table.

- **Return a DVD**

    To return a DVD, you received the same `customer_id` and the rented DVD title. 
    Update the return date in the `rental` table. To get the `rental_id` you can run this query: 

    ```SQL
    select rental_id from rental r, inventory i, film f where r.customer_id = 473 and r.inventory_id = i.inventory_id and i.film_id = f.film_id and f.film_id = 418;
    ```

### Part 3

- **Find Overdue DVDs**

    Many DVD stores produce a daily list of overdue rentals so that customers can be contacted and asked to return their overdue DVDs.

    To create such a list, search the rental table for films with a return date that is NULL and where the rental date is further in the past than the rental duration specified in the film table. If so, the film is overdue and we should produce the name of the film along with the customer name and phone number. For example, the query should return (in the correct API/CLI format):

    ```
        +------------------+--------------+------------------+
        | customer         | phone        | title            |
        +------------------+--------------+------------------+
        | OLVERA, DWAYNE   | 62127829280  | ACADEMY DINOSAUR |
        | HUEY, BRANDON    | 99883471275  | ACE GOLDFINGER   |
        | BROWN, ELIZABETH | 10655648674  | AFFAIR PREJUDICE |
        | OWENS, CARMEN    | 272234298332 | AFFAIR PREJUDICE |
        | HANNON, SETH     | 864392582257 | AFRICAN EGG      |
        +------------------+--------------+------------------+
    ```

### Use of Docker image
We like Docker, so we use it (almost) everywere, this test is no exception :wink:. If you don't know how to use docker don't worry, only a few basic steps are required to be up and running.

This excercise use a docker image with PostgreSQL 11 and the [Sakila](https://www.jooq.org/sakila) database loaded (check the link to see the schema). The user, password and database name is `sakila`.

The project contains the `Dockerfile` to build the image in the docker folder, so you can build it running `docker build -t sakila-img .` inside that folder.

For convenience, the Maven `pom.xml` project contains a profile to build the image directly from Maven, just run `./mvnw generate-resources -Pdocker`.

The generated image name is `sakila-img`, simply run `docker run -d -p 5432:5432 --name sakila-pg sakila-img` to create a container named `sakila-pg`.
Now you can connect to the PostgreSQL database with `psql -h localhost -p 5432 sakila sakila` or using the IP of the Docker container, depends on the operative system. 


# F.A.Q.

*Do you have to complete all the tests?* Ideally yes, but don’t worry if you don’t have enough time to finish all the exercises. We’re going to value the clean code and the correctness of the solution.

*Is it OK to share your solutions publicly?* Yes, the questions are not prescriptive, the process and discussion around the code is the valuable part. You do the work, you own the code. Given we are asking you to give up your time, it is entirely reasonable for you to keep and use your solution as you see fit.

*Should I do X?* For any value of X, it is up to you, we intentionally leave the problem a little open-ended and will leave it up to you to provide us with what you see as important. Just remember the rough time frame of the project. If it is going to take you a couple of days, it isn't essential.

*Something is ambiguous, and I don't know what to do?* The first thing is: don't get stuck. We really don't want to trip you up intentionally, we are just attempting to see how you approach problems. That said, there are intentional ambiguities in the specifications, mainly to see how you fill in those gaps, and how you make design choices. If you really feel stuck, our first preference is for you to make a decision and document it with your submission - in this case, there is really no wrong answer. If you feel it is not possible to do this, just send us an email and we will try to clarify or correct the question for you.

### **Good luck!**
