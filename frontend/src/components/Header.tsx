import React, { useState, useEffect } from 'react';
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Avatar, AvatarImage, AvatarFallback } from "@/components/ui/avatar";
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from "@/components/ui/dropdown-menu";

const Header: React.FC = () => {
  const [username, setUsername] = useState<string | null>(null);

  useEffect(() => {
    // Check for username in localStorage
    const storedUsername = localStorage.getItem("username");
    setUsername(storedUsername);
  }, []);

  const handleSignOut = () => {
    localStorage.removeItem("username");
    setUsername(null);
  };

  return (
    <header className="sticky flex items-center justify-between px-6 py-4 shadow-md">
      {/* Website Name */}
      <a href="/" className="text-2xl font-bold text-blue-600 hover:text-blue-800">
        (prawie) Allegro
      </a>

      {/* Smaller Search Bar */}
      <div className="w-1/3 mx-4">
        <Input
          type="text"
          placeholder="Search..."
          className="w-full h-8 text-sm"
        />
      </div>

      {/* Auth Buttons or Avatar */}
      <div className="flex space-x-4">
        {!username ? (
          <>
            <Button variant="outline" className="text-sm" onClick={() => window.location.href = '/auth'}>
              Sign In
            </Button>
            <Button className="text-sm" onClick={() => window.location.href = '/signup'}>
              Sign Up
            </Button>
          </>
        ) : (
          <DropdownMenu>
            <DropdownMenuTrigger>
              <Avatar className="cursor-pointer">
                <AvatarImage src="/path-to-avatar-image.jpg" alt={username} />
                <AvatarFallback>{username.charAt(0).toUpperCase()}</AvatarFallback>
              </Avatar>
            </DropdownMenuTrigger>
            <DropdownMenuContent align="end">
              <DropdownMenuItem onClick={() => alert('View Cart')}>
                View Cart
              </DropdownMenuItem>
              <DropdownMenuItem onClick={() => alert('Options')}>
                Options
              </DropdownMenuItem>
              <DropdownMenuItem onClick={handleSignOut}>
                Sign Out
              </DropdownMenuItem>
            </DropdownMenuContent>
          </DropdownMenu>
        )}
      </div>
    </header>
  );
};

export default Header;
