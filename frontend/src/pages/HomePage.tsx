import React from 'react';
import Header from '../components/Header';
import FetchProductButton from '../components/FetchProductButton';

const HomePage = () => {
    return (
        <div>
            <Header />
            <h1>Product Fetcher</h1>
            <FetchProductButton />
        </div>
    );
};

export default HomePage;