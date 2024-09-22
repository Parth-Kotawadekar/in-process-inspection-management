import logo from './logo.svg';
import './App.css';
import { Routes, Route } from 'react-router-dom';
import NavigationBar from './Components/Navigationbar/NavigationBar';

function App() {
  return (
    <>
      <NavigationBar/>

      
        <Routes>

          <Route path='/employee'></Route>

        </Routes>
    </>
  );
}

export default App;
