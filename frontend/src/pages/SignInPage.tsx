import SignInForm from '@/components/SignInForm';
import React from 'react';
import { Button } from '@/components/ui/button';

const SignInPage: React.FC = () => {
    return (
        <div>
            <SignInForm />
            <Button variant='outline' className="w-full max-w-sm p-4 mx-auto rounded-lg shadow-md space-y-4" onClick={() => window.location.href = '/signup'}>
                Don't have an account? Sign Up
            </Button>
        </div>
    );
};

export default SignInPage;