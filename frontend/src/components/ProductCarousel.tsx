import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Carousel } from '@/components/ui/carousel';
import { Card, CardContent, CardDescription, CardTitle } from '@/components/ui/card';
import { Button } from '@/components/ui/button';

interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
  imageUrl: string;
  stockQuantity: number;
  isAvailable: boolean;
  viewCount: number;
  dateAdded: string;  
  discount: number;
}

interface ProductCarouselProps {
  type: string; // Prop to specify the type of products to display
}

const getTitle = (type) => {
  if (type === 'newest') {
    return 'Newest Products';
  } else if (type === 'most-viewed') {
    return 'Most Viewed Products';
  } else if (type === 'discounted') {
    return 'Products with Discounts';
  } else {
    return 'Default Title';
  }
};

export function ProductCarousel({ type }: ProductCarouselProps) {
  const [products, setProducts] = useState<Product[]>([]);

  useEffect(() => {
    fetchProducts();
  }, [type]); // Refetch products when `type` changes

  const fetchProducts = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/products/${type}`);  // Pass type
      setProducts(response.data);
      console.log(response.data);
    } catch (error) {
      console.error("Error fetching products:", error);
    }
  };

  return (
    <div>
      <div style={{ display: 'flex', justifyContent: 'space-between', padding: '16px 48px 8px 48px' }}>
                <h2 className="text-xl font-bold">
                    {getTitle(type)}
                </h2>
                <Button variant = "outline">View All Products</Button>
      </div>
      <div>
          <div style={{ padding: '0px 16px 32px 16px', display: 'flex', justifyContent: 'center' }}> {/* Padding and center alignment */}
          <Carousel style={{ 
            display: 'flex', 
            flexDirection: 'row', 
            overflowX: 'auto',  // Horizontal scroll only
            maxWidth: 'calc(100% - 32px)', // Max width minus padding
          }}>
            {products.map(product => (
              <Card key={product.id} style={{ minWidth: '180px', maxWidth: '180px', margin: '0px 8px 0px 8px' }}> {/* Card styles */}
                <CardContent className='pt-4'>
                <img src={product.imageUrl} alt={product.name} className="w-32 h-32 object-cover rounded-md" />
                  <CardTitle className=' pt-4 min-h-[70px]'>{product.name}</CardTitle>
                  <CardDescription>
                    <p>{product.dateAdded}</p>
                    <p>{product.stockQuantity} in stock</p>
                    <p>Views: {product.viewCount}</p>
                  </CardDescription>
                  {(product.discount === 0) && <p>{product.price.toFixed(2)}</p>}
                  {(product.discount > 0) && <p className='text-green-500 font-bold'>{(product.price - parseFloat(product.discount.toFixed(2))).toFixed(2)}</p>}
                  {(product.discount > 0) && <p className='text-gray-500 line-through text-sm'>{product.price}</p>}
                </CardContent>
              </Card>
            ))}
          </Carousel>
        </div>
      </div>
    </div>
  );
}

export default ProductCarousel;