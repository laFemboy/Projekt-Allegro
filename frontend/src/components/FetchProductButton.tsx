import React, { useState } from 'react';
import axios from 'axios';
import { Button } from "@/components/ui/button"; // Adjust the import path as needed

interface Product {
  id: number;
  name: string;
  description: string;
  category: string;
  subCategory: string;
  price: number;
  discount: number;
  dateAdded: string;
  imageUrl: string;
  stockQuantity: number;
  isAvailable: boolean;
  viewCount: number;
}

const FetchProductButton: React.FC = () => {
  const [product, setProduct] = useState<Product | null>(null);
  const [error, setError] = useState<string | null>(null);

  const fetchProductById = async (id: number) => {
    try {
      setError(null); // Clear previous errors
      const response = await axios.get(`http://localhost:8080/api/products/${id}`);
      setProduct(response.data); // Set the fetched product
    } catch (err) {
      if (axios.isAxiosError(err) && err.response?.status === 404) {
        setError('Product not found');
      } else {
        setError('An error occurred while fetching the product');
      }
    }
  };

  return (
    <div>
      <Button onClick={() => fetchProductById(1)}>Fetch Product</Button>

      {error && <p style={{ color: 'red' }}>{error}</p>}
      
      {product && (
        <div>
          <h2>{product.name}</h2>
          <p>{product.description}</p>
          <p>Category: {product.category}</p>
          <p>Price: ${product.price}</p>
        </div>
      )}
    </div>
  );
};

export default FetchProductButton;