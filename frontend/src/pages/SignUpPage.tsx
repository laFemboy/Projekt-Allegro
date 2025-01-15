import { SignUpForm } from '@/components/SignUpForm';
import { Button } from '@/components/ui/button';
import React from 'react';

const SignUpPage: React.FC = () => {
    return (
        <div>
            <SignUpForm />
            <Button variant='outline' className="w-full max-w-sm p-4 mx-auto rounded-lg shadow-md space-y-4" onClick={() => window.location.href = '/auth'}>
                Already have an account? Sign In
            </Button>
        </div>
    );
};

export default SignUpPage;