import { SignUpForm } from '@/components/SignUpForm';
import { Button } from '@/components/ui/button';
import React from 'react';

const SignUpPage: React.FC = () => {
    return (
        <div>
            <a href="/" className="text-2xl font-bold text-blue-600 hover:text-blue-800">
                (prawie) Allegro
            </a>
            <SignUpForm />
        </div>
    );
};

export default SignUpPage;