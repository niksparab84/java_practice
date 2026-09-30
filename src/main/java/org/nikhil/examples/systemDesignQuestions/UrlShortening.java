package org.nikhil.examples.systemDesignQuestions;

public class UrlShortening {

    //Problem: Design a URL shortening service like Bitly.
    //Hint: This is a very common question. Walk through the key components: API design, database schema (SQL vs. NoSQL),
    // hash function for generating the short URL, redirect mechanism and scalability considerations (handling a high volume of reads vs. writes).
    // Discuss how you'd handle collisions and what a high-level architecture diagram would look like.

    // Solution:

    // 1. API Design:
    // POST /shorten: Accepts a long URL and returns a shortened URL.
    // GET /{shortUrl}: Redirects to the original long URL.

    // 2. Database Schema:
    // Use a NoSQL database like DynamoDB or MongoDB for scalability.
    // Table: UrlMapping
    // Columns: shortUrl (Primary Key), longUrl, createdAt, expirationDate

    // 3. Hash Function:
    // Use a base62 encoding (0-9, a-z, A-Z) to
    // generate a unique short URL from a unique ID. This provides a large number of combinations with a small URL length.

    // 4. Redirect Mechanism:
    // When a GET request is made to /{shortUrl}, look up the shortUrl in the database and redirect to the corresponding longUrl.

    // 5. Scalability Considerations:
    // Use caching (e.g., Redis) to store frequently accessed URLs to reduce database load.
    // Implement load balancing to distribute incoming requests across multiple servers.

    // 6. Handling Collisions:
    // Use a unique ID generator (like UUID or a sequence from the database) to ensure that each short URL is unique.
    // If a collision occurs (which is rare with a good hash function), regenerate the short URL.

    // 7. High-Level Architecture:
    // Client -> Load Balancer -> Application Servers -> NoSQL Database -> Cache (Redis)

    // Note: This is a high-level overview. Each component can be further detailed based on specific requirements and constraints.
    // Java implementation would involve setting up RESTful endpoints, database connections, and caching mechanisms.
    // Due to the complexity and scope, a full implementation is beyond this format.
    // However, this outline provides a solid foundation for designing a URL shortening service.

    // Java class diagram representation (simplified):
    class UrlShortenerService {
        // Method to shorten a URL
        public String shortenUrl(String longUrl) {
            // Generate a unique short URL
            // Store the mapping in the database
            return "shortUrl"; // Placeholder
        }

        // Method to retrieve the original URL
        public String getLongUrl(String shortUrl) {
            // Look up the short URL in the database
            return "longUrl"; // Placeholder
        }
    }

}
