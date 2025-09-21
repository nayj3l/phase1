package com.example.phase1.service;

import org.springframework.stereotype.Service;

import com.example.phase1.model.CheckResponse;

@Service
public class CheckService {

    public CheckResponse checkForC2(int n) {
        if (n <= 0) return null;

        double sqrt = Math.sqrt(n);
        if (Math.floor(sqrt) != sqrt) {
            return null;
        }

        int c = (int) sqrt;
        int c2 = c * c;
        for (int a = 1; a < c; a++) {
            int a2 = a * a;
            int b2 = c2 - a2;
            double bDouble = Math.sqrt(b2);
            if (Math.floor(bDouble) == bDouble) {
                int b = (int) bDouble;
                double avg = (a + b + c) / 3.0;
                return new CheckResponse(a, b, c, avg);
            }
        }
        return null;
    }
}
