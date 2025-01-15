import SignInForm from '@/components/SignInForm';
import React from 'react';
import { Button } from '@/components/ui/button';

const SignInPage: React.FC = () => {
    return (
        <div>
            <a href="/" className="text-2xl font-bold text-blue-600 hover:text-blue-800">
                (prawie) Allegro
            </a>
            <SignInForm />
        </div>
    );
};

export default SignInPage;