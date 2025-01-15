import React from 'react';
import Header from '../components/Header';
import FetchProductButton from '../components/FetchProductButton';
import ProductCarousel from '@/components/ProductCarousel';

const HomePage = () => {
    return (
        <div>
            <Header />
            {/* <h1>Product Fetcher</h1>
            <FetchProductButton /> */}
            <ProductCarousel type = 'newest'/>
            <ProductCarousel type = 'most-viewed'/>
            <ProductCarousel type = 'discounted'/>
        </div>
    );
};

export default HomePage;