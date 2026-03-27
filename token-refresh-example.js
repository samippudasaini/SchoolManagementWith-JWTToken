// Frontend JavaScript example for handling token expiration and refresh

class TokenManager {
    constructor() {
        this.baseURL = 'http://localhost:8080';
        this.token = localStorage.getItem('authToken');
    }

    // Method to make API calls with automatic token refresh
    async makeAuthenticatedRequest(url, options = {}) {
        try {
            // Add token to headers
            const headers = {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${this.token}`,
                ...options.headers
            };

            const response = await fetch(url, {
                ...options,
                headers
            });

            // If token expired (401), try to refresh
            if (response.status === 401) {
                const refreshed = await this.refreshToken();
                if (refreshed) {
                    // Retry the original request with new token
                    headers['Authorization'] = `Bearer ${this.token}`;
                    return await fetch(url, { ...options, headers });
                } else {
                    // Redirect to login if refresh failed
                    this.redirectToLogin();
                    return null;
                }
            }

            return response;
        } catch (error) {
            console.error('API request failed:', error);
            throw error;
        }
    }

    // Method to refresh the token
    async refreshToken() {
        try {
            const response = await fetch(`${this.baseURL}/api/auth/refresh-token`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${this.token}`
                }
            });

            if (response.ok) {
                const data = await response.json();
                if (data.success && data.data.token) {
                    this.token = data.data.token;
                    localStorage.setItem('authToken', this.token);
                    console.log('Token refreshed successfully');
                    return true;
                }
            }
            
            console.log('Token refresh failed');
            return false;
        } catch (error) {
            console.error('Token refresh error:', error);
            return false;
        }
    }

    // Redirect to login page
    redirectToLogin() {
        localStorage.removeItem('authToken');
        window.location.href = '/login';
    }

    // Set token after login
    setToken(token) {
        this.token = token;
        localStorage.setItem('authToken', token);
    }

    // Clear token on logout
    clearToken() {
        this.token = null;
        localStorage.removeItem('authToken');
    }
}

// Usage example:
const tokenManager = new TokenManager();

// Example API call
async function fetchStudents() {
    try {
        const response = await tokenManager.makeAuthenticatedRequest(
            'http://localhost:8080/api/students/get-all'
        );
        
        if (response && response.ok) {
            const students = await response.json();
            console.log('Students:', students);
            return students;
        }
    } catch (error) {
        console.error('Failed to fetch students:', error);
    }
}

// Example usage in your application
// fetchStudents();