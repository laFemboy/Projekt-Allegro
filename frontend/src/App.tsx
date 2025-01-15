import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import FetchProductButton from './components/FetchProductButton'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div style={{ padding: '20px' }}>
      <h1>Product Fetcher</h1>
      <FetchProductButton />
    </div>
  )
}

export default App
