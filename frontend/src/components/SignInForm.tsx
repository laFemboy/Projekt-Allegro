// Import necessary libraries and components
import React from "react";
import { useForm } from "react-hook-form";
import { Button } from "@/components/ui/button";
import { Form, FormControl, FormField, FormItem, FormLabel, FormMessage } from "@/components/ui/form";
import { Input } from "@/components/ui/input";
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";
import axios from "axios";

// Define the schema using Zod
const FormSchema = z.object({
  username: z.string().min(2, { message: "Username must be at least 2 characters." }),
  password: z.string().min(6, { message: "Password must be at least 6 characters." }),
});

export function SignInForm() {
  const form = useForm({
    resolver: zodResolver(FormSchema),
    defaultValues: {
      username: "",
      password: "",
    },
  });

  interface UserCredentials {
    username: string;
    password: string;
  }
  

  const onSubmit = async (data: UserCredentials) => {
    try {
      const response = await axios.post("http://localhost:8080/api/users/signin", data);
      console.log("User authenticated:", response.data);
      localStorage.setItem("username", response.data.username);
      localStorage.setItem("password", response.data.password);
      localStorage.setItem("email", response.data.email);
      window.location.href = '/'


    } catch (error: any) {
      console.error("Authentication error:", error.response?.data?.message || error.message);
    }
  };

  return (
    <Form {...form}>
      <form onSubmit={form.handleSubmit(onSubmit)} className="w-full max-w-md p-6 mx-auto rounded-lg shadow-md space-y-6">
        <FormField
          control={form.control}
          name="username"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Username</FormLabel>
              <FormControl>
                <Input placeholder="Enter your username" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />
        <FormField
          control={form.control}
          name="password"
          render={({ field }) => (
            <FormItem>
              <FormLabel>Password</FormLabel>
              <FormControl>
                <Input type="password" placeholder="Enter your password" {...field} />
              </FormControl>
              <FormMessage />
            </FormItem>
          )}
        />
        <Button type="submit" className="w-full">
          Sign In
        </Button>
        <Button type = 'button' variant='outline' className="w-full max-w-sm p-4 mx-auto rounded-lg shadow-md space-y-4" onClick={() => window.location.href = '/signup'}>
          Don't have an account? Sign Up
        </Button>
      </form>
    </Form>
  );
}

// Export the component
export default SignInForm;
