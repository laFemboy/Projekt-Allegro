import { useState } from 'react'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import FetchProductButton from './components/FetchProductButton'
import Header from './components/Header'
import HomePage from './pages/HomePage.tsx'
import SignInPage from './pages/SignInPage'
import SignUpPage from './pages/SignUpPage'

function App() {
  const [count, setCount] = useState(0)
  // localStorage.setItem("username", "JohnDoe");

  return (
    <div>
        <Router>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/auth" element={<SignInPage />} />
          <Route path="/signup" element={<SignUpPage />} />
        </Routes>
      </Router>
    </div>
  )
}

export default App
