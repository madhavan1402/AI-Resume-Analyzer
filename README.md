# AI Resume Analyzer

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![React](https://img.shields.io/badge/React-18.2.0-blue.svg)](https://reactjs.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0.0-green.svg)](https://spring.io/projects/spring-boot)
[![Hugging Face](https://img.shields.io/badge/Hugging%20Face-Spaces-orange.svg)](https://huggingface.co/spaces)

A full-stack web application that analyzes PDF resumes using machine learning to provide scores, skill extraction, and summaries. Built for recruiters and job seekers to streamline resume evaluation.

## Demo

🚀 **Live Application**: [https://ai-resume-analyzer-dusky-nine.vercel.app/](https://ai-resume-analyzer-dusky-nine.vercel.app/)

- **Frontend**: Hosted on Vercel
- **Backend**: Hosted on Render
- **ML API**: Hosted on Hugging Face Spaces

## Key Features

- **PDF Upload**: Secure multipart file upload for resume PDFs
- **AI Analysis**: Leverages Hugging Face ML models for intelligent parsing
- **Real-time Results**: Instant feedback with scores, skills, and summaries
- **Responsive UI**: Modern React interface optimized for all devices
- **Scalable Backend**: Spring Boot API with robust error handling
- **CORS Enabled**: Configured for cross-origin requests

## Architecture

```
┌─────────────┐    ┌─────────────┐    ┌─────────────┐    ┌─────────────┐
│   Frontend  │ -> │   Backend   │ -> │   ML API    │ -> │   Backend   │
│   (React)   │    │ (Spring Boot)│    │ (Gradio)   │    │ (Response)  │
│             │    │             │    │             │    │             │
│ Upload PDF  │    │ /upload     │    │ /api/predict│    │ JSON Result │
└─────────────┘    └─────────────┘    └─────────────┘    └─────────────┘
```

Data Flow:
1. User uploads PDF via frontend form
2. Backend receives file and forwards to ML API
3. ML API processes content and returns structured data
4. Backend relays results back to frontend for display

## Tech Stack

### Frontend
- **React** (Create React App)
- **Deployment**: Vercel

### Backend
- **Java** with Spring Boot
- **Build Tool**: Maven
- **Deployment**: Render

### ML Service
- **Hugging Face Spaces** with Gradio
- **Model**: Custom resume analysis model (Update this with specific model name)
- **Deployment**: Hugging Face Spaces

### Additional
- **File Handling**: Multipart form-data
- **Environment Config**: .env files for API URLs

## API Documentation

### POST /upload
Upload a PDF resume for analysis.

**Request:**
- Method: `POST`
- Content-Type: `multipart/form-data`
- Body: `file` (PDF file)

**Example Request (cURL):**
```bash
curl -X POST https://ai-resume-analyzer-1df3.onrender.com/upload \
  -F "file=@resume.pdf"
```

**Response:**
```json
{
  "score": 85,
  "skills": ["Java", "Python", "React"],
  "summary": "Experienced software engineer with strong backend skills..."
}
```

**Status Codes:**
- `200`: Success
- `400`: Invalid file format
- `500`: Server error

## Local Setup

### Prerequisites
- Node.js (v16+)
- Java (v17+)
- Maven
- Python (v3.8+)

### Frontend Setup
```bash
cd frontend
npm install
npm start
```
- Access at: `http://localhost:3000`
- Environment: Create `.env` with `REACT_APP_BACKEND_URL=http://localhost:8080`

### Backend Setup
```bash
cd backend
mvn clean install
mvn spring-boot:run
```
- Access at: `http://localhost:8080`
- Environment: Set `ML_API_URL=https://madhavan06-resume-ml.hf.space/api/predict`

### ML API Setup (Local Development)
```bash
cd ml-api
pip install -r requirements.txt
python app.py
```
- Access at: `http://localhost:7860` (Gradio default)

### Full Stack with Docker
```bash
docker-compose up --build
```

## Deployment

### Frontend (Vercel)
1. Connect GitHub repo to Vercel
2. Set environment variable: `REACT_APP_BACKEND_URL=https://ai-resume-analyzer-1df3.onrender.com`
3. Deploy automatically on push

### Backend (Render)
1. Connect GitHub repo to Render
2. Select backend directory
3. Set environment variable: `ML_API_URL=https://madhavan06-resume-ml.hf.space/api/predict`
4. Deploy with Maven build command

### ML API (Hugging Face Spaces)
1. Upload code to Hugging Face Spaces
2. Use Gradio app.py as entry point
3. Spaces handles deployment automatically

## Folder Structure

```
AI-Resume-Analyzer/
├── frontend/                 # React application
│   ├── public/
│   ├── src/
│   ├── package.json
│   └── README.md
├── backend/                  # Spring Boot API
│   ├── src/main/java/com/maddy/backend/
│   ├── pom.xml
│   ├── Dockerfile
│   └── application.properties
├── ml-api/                   # Hugging Face Gradio app
│   ├── app.py
│   ├── requirements.txt
│   └── Dockerfile
├── screenshots/              # App screenshots
├── docker-compose.yml        # Local development
├── README.md                 # This file
└── TODO.md                   # Project tasks
```

## Screenshots

### Home Page
![Home Page](screenshots/home.png)
*Update this with actual screenshot path*

### Analysis Results
![Results](screenshots/results.png)
*Update this with actual screenshot path*

### Upload Interface
![Upload](screenshots/upload.png)
*Update this with actual screenshot path*

## Roadmap

- [ ] Add user authentication and saved analyses
- [ ] Support for multiple file formats (DOCX, TXT)
- [ ] Advanced ML models for better accuracy
- [ ] Batch processing for multiple resumes
- [ ] Integration with job boards (LinkedIn, Indeed)
- [ ] Mobile app version (React Native)

## Troubleshooting

### Render Cold Starts
Backend on Render may take 30-60 seconds to wake up on first request due to free tier limitations.

### CORS Issues
If experiencing CORS errors, ensure `CorsConfig.java` is properly configured in the backend.

### File Upload Limits
Default file size limit is 10MB. For larger files, update Spring Boot configuration.

### ML API Timeouts
Hugging Face Spaces free tier has request limits. Upgrade to paid plan for production use.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author

**Madhavan N**
- GitHub: [https://github.com/madhavan1402]
- LinkedIn: [https://www.linkedin.com/in/madhavan-n-702a49297/]

---

*Built with ❤️ for efficient resume analysis*
