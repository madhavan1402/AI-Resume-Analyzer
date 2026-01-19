import os
from flask import Flask, request, jsonify
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

@app.post("/analyze")
def analyze_resume():
    data = request.get_json()
    text = data.get("text", "")

    if text.strip() == "":
        return jsonify({"error": "No text received"}), 400

    summary = text[:200] + "..."
    skills = extract_skills(text)
    score = len(skills) * 10

    return jsonify({
        "summary": summary,
        "skills": skills,
        "score": score
    })

@app.get("/health")
def health():
    return jsonify({"status": "healthy"})


def extract_skills(text):
    keywords = ["Java", "Python", "Spring", "React", "AWS", "SQL", "Machine Learning", "C++"]
    found = [skill for skill in keywords if skill.lower() in text.lower()]
    return found

if __name__ == "__main__":
    host = '0.0.0.0'
    app.run(host=host, port=5000)
