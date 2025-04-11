# DaanKori - Crowdfunding Platform

DaanKori is a crowdfunding platform built with Spring Boot and MongoDB that allows users to create and contribute to fundraising campaigns.


## About 
This project was undertaken as part of the coursework for the Object-Oriented Design course (Course Code: SE221), conducted under the guidance of [Maidul Islam](https://github.com/Maidul02). The primary objective of this project was to apply core object-oriented programming principles—such as encapsulation, inheritance, polymorphism, and abstraction—in a practical, real-world scenario. Additionally, the project incorporates design patterns and best practices introduced throughout the course, reinforcing both theoretical understanding and hands-on software design skills.


## Postman documention
https://documenter.getpostman.com/view/16185131/2sB2cXA23s

## Features

- User authentication with JWT
- Create and manage fundraising campaigns
- Make donations to campaigns
- Track campaign progress
- View donation history
- Balance management system
- Real-time campaign status updates

## Prerequisites

- Java 17 or higher
- MongoDB
- Maven

## Setup

1. Clone the repository
2. Configure MongoDB connection in `application.properties`
3. Build the project:
   ```bash
   ./mvnw clean install
   ```
4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

## API Endpoints

### Authentication
- POST `/api/auth/register` - Register a new user
- POST `/api/auth/login` - Login and get JWT token
- POST `/api/auth/topup` - Top up user balance
- GET `/api/auth/user` - Get authenticated user details

### Campaigns
- GET `/api/campaigns` - Get all active campaigns
- GET `/api/campaigns/{id}` - Get campaign by ID
- POST `/api/campaigns` - Create a new campaign
- DELETE `/api/campaigns/{id}` - Delete a campaign
- GET `/api/campaigns/user` - Get user's campaigns

### Donations
- POST `/api/donations` - Make a donation
- GET `/api/donations/user` - Get user's donation history
- GET `/api/donations/campaign/{campaignId}` - Get campaign's donations

## API Examples

### Authentication Examples

#### Register a New User
```http
POST /api/auth/register
Content-Type: application/json

{
    "username": "john_doe",
    "email": "john@example.com",
    "password": "securePassword123",
    "fullName": "John Doe"
}

Response:
{
    "status": "success",
    "message": "User registered successfully"
}
```

#### Login
```http
POST /api/auth/login
Content-Type: application/json

{
    "username": "john_doe",
    "password": "securePassword123"
}

Response:
{
    "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

#### Get User Details
```http
GET /api/auth/user
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...

Response:
{
    "username": "john_doe",
    "fullName": "John Doe",
    "email": "john@example.com",
    "balance": 1000.00
}
```

#### Top Up Balance
```http
POST /api/auth/topup
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
Content-Type: application/json

{
    "amount": 1000.00
}

Response:
{
    "message": "Balance topped up successfully!",
    "newBalance": 1000.00
}
```

### Campaign Examples

#### Create Campaign
```http
POST /api/campaigns
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
Content-Type: application/json

{
    "title": "Help Build a School",
    "description": "Raising funds to build a primary school in rural Bangladesh",
    "targetAmount": 50000.00,
    "deadline": "2024-12-31",
    "category": "EDUCATION"
}

Response:
{
    "id": "1",
    "title": "Help Build a School",
    "currentAmount": 0.00,
    "targetAmount": 50000.00,
    "status": "ACTIVE"
}
```

#### Get All Campaigns
```http
GET /api/campaigns

Response:
{
    "campaigns": [
        {
            "id": "1",
            "title": "Help Build a School",
            "description": "Raising funds to build a primary school...",
            "currentAmount": 5000.00,
            "targetAmount": 50000.00,
            "deadline": "2024-12-31",
            "status": "ACTIVE"
        },
        // ... more campaigns
    ]
}
```

#### Get Campaign by ID
```http
GET /api/campaigns/1

Response:
{
    "id": "1",
    "title": "Help Build a School",
    "description": "Raising funds to build a primary school...",
    "currentAmount": 5000.00,
    "targetAmount": 50000.00,
    "deadline": "2024-12-31",
    "status": "ACTIVE",
    "creator": {
        "username": "john_doe",
        "fullName": "John Doe"
    }
}
```

### Donation Examples

#### Make a Donation
```http
POST /api/donations
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
Content-Type: application/json

{
    "campaignId": "1",
    "amount": 100.00,
    "message": "Good luck with the school project!"
}

Response:
{
    "status": "success",
    "message": "Donation successful",
    "donationId": "65f7a2b3c4d5e6f7g8h9i0j2",
    "campaignTitle": "Help Build a School",
    "amount": 100.00
}
```

#### Get User's Donations
```http
GET /api/donations/user
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...

Response:
{
    "donations": [
        {
            "id": "1",
            "campaignTitle": "Help Build a School",
            "amount": 100.00,
            "date": "2024-03-15T14:30:00Z",
            "message": "Good luck with the school project!"
        },
        // ... more donations
    ]
}
```

#### Get Campaign Donations
```http
GET /api/donations/campaign/65f7a2b3c4d5e6f7g8h9i0j1

Response:
{
    "campaignTitle": "Help Build a School",
    "donations": [
        {
            "id": "65f7a2b3c4d5e6f7g8h9i0j2",
            "donor": "john_doe",
            "amount": 100.00,
            "date": "2024-03-15T14:30:00Z",
            "message": "Good luck with the school project!"
        },
        // ... more donations
    ],
    "totalDonations": 5000.00
}
```

## Security

The application uses JWT (JSON Web Token) for authentication. Include the JWT token in the Authorization header for protected endpoints:
```
Authorization: Bearer <token>
```

## Error Handling

The application includes comprehensive error handling for:
- Invalid authentication
- Insufficient balance
- Campaign not found
- Unauthorized actions
- Duplicate username/email

### Example Error Responses

#### Invalid Authentication
```http
Status: 401 Unauthorized
{
    "error": "Invalid credentials",
    "message": "Username or password is incorrect"
}
```

#### Insufficient Balance
```http
Status: 400 Bad Request
{
    "error": "Insufficient balance",
    "message": "Your current balance is insufficient for this donation"
}
```

#### Resource Not Found
```http
Status: 404 Not Found
{
    "error": "Resource not found",
    "message": "Campaign with ID '1' not found"
}
```

