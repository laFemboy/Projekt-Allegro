import React from 'react';
import Header from '../components/Header';
import FetchProductButton from '../components/FetchProductButton';
import ProductCarousel from '@/components/ui/ProductCarousel';

const HomePage = () => {
    return (
        <div>
            <Header />
            <h1>Product Fetcher</h1>
            <FetchProductButton />
            <ProductCarousel />
            <ProductCarousel />
            <ProductCarousel />
        </div>
    );
};

export default HomePage;