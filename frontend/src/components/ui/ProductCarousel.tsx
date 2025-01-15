import React from 'react';
import { Carousel } from '@/components/ui/carousel'; // Assuming there's a Carousel component
import { Card, CardContent, CardTitle, CardDescription } from '@/components/ui/card'; // Correct imports
import { Button } from './button';

const products = Array(6).fill({
  id: Math.random(),
  name: 'Placeholder Product',
  description: 'This is a placeholder description for the product.',
  price: '$0.00',
  imageUrl: '/placeholder-image.jpg', // Use a placeholder image URL
});

export function ProductCarousel() {
    return (
        <div>
            <div style={{ display: 'flex', justifyContent: 'space-between', padding: '0px 48px 8px 48px' }}>
                <h2 className="text-xl font-bold">Our new products:</h2>
                <Button variant = "outline">View All Products</Button>
            </div>
      <div style={{ padding: '0px 16px 32px 16px', display: 'flex', justifyContent: 'center' }}> {/* Padding and center alignment */}
        <Carousel style={{ 
          display: 'flex', 
          flexDirection: 'row', 
          overflowX: 'auto',  // Horizontal scroll only
          maxWidth: 'calc(100% - 32px)', // Max width minus padding
        }}>
          {products.map(product => (
            <Card key={product.id} style={{ minWidth: '150px', maxWidth: '200px', margin: '0 8px' }}> {/* Card styles */}
              <CardContent>
                <CardTitle>{product.name}</CardTitle>
                <CardDescription>{product.description}</CardDescription>
                <p>{product.price}</p>
              </CardContent>
            </Card>
          ))}
        </Carousel>
      </div>
        </div>
    );
  }
  
  
  

export default ProductCarousel;
