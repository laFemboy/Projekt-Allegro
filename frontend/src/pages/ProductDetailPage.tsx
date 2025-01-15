import React from 'react';
import { useParams } from 'react-router-dom';

const ProductDetailPage: React.FC = () => {
    const { productId } = useParams<{ productId: string }>();

    // Mock product data
    const product = {
        id: productId,
        name: 'Sample Product',
        description: 'This is a sample product description.',
        price: 99.99,
        imageUrl: 'https://via.placeholder.com/150'
    };

    return (
        <div>
            <h1>{product.name}</h1>
            <img src={product.imageUrl} alt={product.name} />
            <p>{product.description}</p>
            <p>Price: ${product.price}</p>
        </div>
    );
};

export default ProductDetailPage;