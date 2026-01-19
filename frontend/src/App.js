
import React, { useState } from 'react';
import './App.css';

function App() {
  const [file, setFile] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [result, setResult] = useState(null);

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
    setError('');
    setResult(null);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!file) {
      setError('Please select a PDF file.');
      return;
    }

    setLoading(true);
    setError('');
    setResult(null);

    const formData = new FormData();
    formData.append('file', file);

    try {
      const response = await fetch('${process.env.REACT_APP_BACKEND_URL}/upload', {
        method: 'POST',
        body: formData,
      });

      const data = await response.json();

      if (!response.ok) {
        throw new Error(data.error || 'Failed to upload file. Please try again.');
      }

      if (data.error) {
        throw new Error(data.error);
      }

      setResult(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="app">
      <h1>AI Resume Analyzer</h1>
      <form onSubmit={handleSubmit} className="upload-form">
        <input
          type="file"
          accept=".pdf"
          onChange={handleFileChange}
          className="file-input"
        />
        <button type="submit" disabled={loading} className="upload-btn">
          {loading ? 'Analyzing...' : 'Upload Resume'}
        </button>
      </form>
      {loading && <p className="loading">Processing your resume...</p>}
      {error && <p className="error">{error}</p>}
      {result && (
        <div className="results">
          <h2>Analysis Results</h2>
          <p><strong>Score:</strong> {result.score}/100</p>
          <div>
            <strong>Skills:</strong>
            <ul>
              {result.skills.map((skill, index) => (
                <li key={index}>{skill}</li>
              ))}
            </ul>
          </div>
          <div>
            <strong>Summary:</strong>
            <p>{result.summary}</p>
          </div>
        </div>
      )}
    </div>
  );
}

export default App;

